package libtest;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.LibraryStarter;
import com.example.constant.TimeFormatConstant;
import com.example.entity.LibInfoEntity;
import com.example.entity.MusicInfoEntity;
import com.example.entity.OracleEntity;
import com.example.mapper.AuthSecurityMapper;
import com.example.mapper.BpmnMapper;
import com.example.mapper.LibInfoMapper;
//import com.example.mapper.OracleMapper;
import com.example.pojo.User;
import com.example.properties.UnpackFileUtils;
import com.example.service.BpmnDownloadService;
import com.example.service.EmailSender;
import com.example.service.LeaveService;
import com.example.service.MusicInfoService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/4/2 17:07
 */
@SpringBootTest(classes = LibraryStarter.class)
@RunWith(SpringRunner.class)
public class LibraryTest {
    @Autowired
    private LibInfoMapper libInfoMapper;

    @Autowired
    private MusicInfoService musicInfoService;

    @Autowired
    private AuthSecurityMapper authSecurityMapper;

    @Autowired
    private BpmnMapper bpmnMapper;

    @Test
    public void testSecurity() {
        User zhangsan = authSecurityMapper.getUserInfoByUsername("zhangsan");

        System.out.println(zhangsan);
    }

    @Test
    public void test1() {
        List<LibInfoEntity> libInformations =
                libInfoMapper.getLibInformations(Arrays.asList(1L, 2L, 3L, 4L, 5L));
        System.out.println("获取结果集: "+libInformations);
    }

    @Test
    public void test2() {
//        List<LibInfoEntity> content = libInfoMapper.getLibInfoBySearchContent("鲁滨逊");
//        System.out.println(content);
    }

    @Test
    public void test3() throws IOException {
        InputStream inputStream = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\content\\music.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Pattern pattern = Pattern.compile("<div>.*?</div>");
        Matcher matcher = null;
        UpdateWrapper<MusicInfoEntity> wrapper = null;
        String line;
        int count = 1;
        MusicInfoEntity musicInfoEntity = null;
        while ((line = bufferedReader.readLine()) != null) {
            matcher = pattern.matcher(line);
            while (matcher.find()) {
               String result = matcher.group()
                        .replaceAll("<div>", "")
                        .replaceAll("</div>", "")
                        .replaceAll("&nbsp;", "")
                        .replaceAll("<br>", "")
                        .replaceAll("第\\d+首", "").trim();
                if (count % 2 != 0) {
                    musicInfoEntity = new MusicInfoEntity();
                    musicInfoEntity.setMusicId(Long.valueOf(count));
                    musicInfoEntity.setMusicName(result);
                    musicInfoService.getBaseMapper().insert(musicInfoEntity);
                } else {
                    musicInfoEntity = new MusicInfoEntity();
                    musicInfoEntity.setMusicUrl(result);
                    wrapper = new UpdateWrapper<MusicInfoEntity>().eq("music_id", count - 1);
                    musicInfoService.getBaseMapper().update(musicInfoEntity, wrapper);
                }
                count++;
            }
        }

        bufferedReader.close();
        inputStream.close();
    }

    @Test
    public void test4() {

        musicInfoService.getMusicInfo().getMusicData().stream().forEach(it -> {
            try {
                System.out.println("正在写入");
                URL url = new URL(it.getMusicUrl());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestProperty("accept", "*/*");
//                connection.setRequestProperty("connection", "Keep-Alive");
//                connection.setRequestProperty("user-agent", "PostmanRuntime/7.31.3");
//                connection.setRequestProperty("Content-Type", "audio/mpeg");
//                connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
                connection.setRequestProperty("Host", "webftp.bbs.hnol.net");
                InputStream inputStream = connection.getInputStream();
                OutputStream outputStream = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\content\\"+it.getMusicName()+".mp3");
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, len);
                }
                System.out.println(it.getMusicName()+ " 写入完成");
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void test5() throws IOException {
        File file = new File("C:\\Users\\Lenovo\\Desktop\\文件夹\\vue\\library-system\\src\\assets\\mp3");
        File[] files = file.listFiles();
        List<MusicInfoEntity> musicInfoEntities = new LinkedList<>();
        MusicInfoEntity musicInfoEntity = null;
        Long count = 104L;
        for (File f : files) {
            musicInfoEntity = new MusicInfoEntity();
            musicInfoEntity.setMusicId(count);
            musicInfoEntity.setMusicName(f.getName());
            musicInfoEntity.setMusicUrl(f.getCanonicalPath());
            musicInfoEntity.setCanPlay(1);
            if (f.getName().length() <= 50) {
                musicInfoEntities.add(musicInfoEntity);
                count++;
            }
        }
        musicInfoService.saveBatch(musicInfoEntities);
    }

    @Test
    public void test6() {
        File file = new File("C:\\Users\\Lenovo\\Desktop\\文件夹\\vue\\library-system\\src\\assets\\mp3");
        File[] files = file.listFiles();
        List<String> music_collection = new ArrayList<>();
        for (File file1 : files) {
            System.out.println(file1.getName());
            music_collection.add(file1.getName());
        }

        Wrapper<MusicInfoEntity> wrapper = new QueryWrapper<>();
        List<MusicInfoEntity> musicInfoEntities = musicInfoService.getBaseMapper().selectList(wrapper);
        System.out.println(musicInfoEntities);

        musicInfoEntities.forEach(it -> {
            if (!music_collection.contains(it.getMusicName())) {
                musicInfoService.remove(new QueryWrapper<MusicInfoEntity>().eq("music_name", it.getMusicName()));
            }
        });


        QueryWrapper<MusicInfoEntity> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("music_name", "DJ - 乌兰图雅 (红红的萨日朗Dj By Dj阿利).mp3");
        musicInfoService.getBaseMapper().delete(wrapper1);
    }

    @Autowired
    private UnpackFileUtils unpackFileUtils;

    @Test
    public void test7() {
        System.out.println(unpackFileUtils);
    }

    @Test
    public void test8() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream("C:\\Users\\Lenovo\\eclipse-workspace\\idea-library\\library-info\\target\\classes\\objectlist.properties");
        properties.load(inputStream);
        Set<String> set = properties.stringPropertyNames();
        for (String s : set) {
            System.out.println("key: " + s + "  "+"value: "+properties.getProperty(s));
        }

        properties.put("hello", "world");

        inputStream.close();
    }

    @Test
    public void test9() throws IOException {
        OutputStream outputStream = new FileOutputStream("C:\\Users\\Lenovo\\eclipse-workspace\\idea-library\\library-info\\target\\classes\\objectlist.properties", true);
        BufferedWriter bufferedOutputStream = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedOutputStream.write("\nhello = world\n");
        bufferedOutputStream.close();
        outputStream.close();
    }

    @Autowired
    private EmailSender emailSender;

    @Test
    public void test10() {
        emailSender.sendEmail("test");
    }

    @Test
    public void test11() throws IOException, MessagingException {
        emailSender.sendHtmlEmail("test");
    }

    @Test
    public void test12() throws IOException, MessagingException {
        emailSender.sendAttachmentMail("test");
    }

    @Autowired
    private BpmnDownloadService bpmnDownloadService;

    @Test
    public void test13() throws IOException {
        boolean requireDays_1 = bpmnDownloadService.getAndSaveBpmnModel("requireDays_1");
        if (requireDays_1) {
            System.out.println("save successfully");
        }
    }

//    @Autowired
//    private RequireDayActivitiService requireDayActivitiService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Test
    public void test15() {
        List<ProcessDefinition> requireDays_1 = repositoryService.createProcessDefinitionQuery().processDefinitionKey("requireDays_1").list();

        System.out.println(requireDays_1.get(0).getDescription());
    }

    @Test
    public void test16() {
        Map<String, Object> map = new HashMap<>();
        map.put("pm", "zhangsan");
        map.put("spm", "lisi");
        map.put("hr", "wangwu");

        runtimeService.startProcessInstanceByKey("requireDays_1", map);

    }

    @Autowired
    private TaskService taskService;

    @Test
    public void test17() {
        Map<String, Object> map = new HashMap<>();
        map.put("creator", "chenlei");
        map.put("pm", "zhangsan");
        map.put("spm", "lisi");
        map.put("hr", "wangwu");
        runtimeService.startProcessInstanceByKey("requireDays_2", map);
    }

    @Autowired
    private LeaveService leaveService;

    @Test
    public void test18() {
        Map<String, Object> variable = new HashMap<>();
        variable.put("creator", "陈磊");
        variable.put("hr", "汪涵");
        variable.put("pm", "李虎");
        variable.put("spm", "杨帆");
        variable.put("extra", "请假流程");
        ProcessInstance processInstance = leaveService.startProcess("leave1", variable);

        System.out.println("process start "+processInstance.getName()+"successfully");
    }

    @Test
    public void test19() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("张三").list();
        for (Task task : list) {
            System.out.println("指派人: "+task.getAssignee());
            System.out.println("任务创建时间: "+ TimeFormatConstant.DEFAULT_DATEFORMAT.format(task.getCreateTime()));
        }
//        System.out.println(list);
    }

//    @Autowired
//    private OracleMapper oracleMapper;
//
//    @Test
//    public void test20() {
//        OracleEntity oracleEntity = oracleMapper.selectById(1);
//        System.out.println(oracleEntity);
//    }

    private String getHexString(byte[] md5) {
        int len = md5.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < len; i++) {
            stringBuffer.append(Integer.toHexString(md5[i]));
        }
        return stringBuffer.toString();
    }

    public String getMD5Two(String content) {
        StringBuffer sb = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(FileUtils.readFileToByteArray(new File(path)));
            byte b[] = md.digest(content.getBytes());
            int d;
            for (int i = 0; i < b.length; i++) {
                sb.append(Integer.toHexString((b[i] >> 4) & 0xf));
                sb.append(Integer.toHexString(b[i] & 0xf));
//                d = b[i];
//                if (d < 0) {
//                    d = b[i] & 0xff;
//                    // 与上一行效果等同
//                    // i += 256;
//                }
//                if (d < 16)
//                    sb.append("0");
//                sb.append(Integer.toHexString(d));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

//    0fdfa5e5a88bebae640a5d88e7c84708

//    5eb63bbbe01eeed093cb22bb8f5acdc3
    @Test
    public void test21() {
//        System.out.println(getMD5Two("hello world"));
    }

}
