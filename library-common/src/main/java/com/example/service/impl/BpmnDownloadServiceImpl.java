package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.constant.FileSystemConstant;
import com.example.entity.BpmnModelEntity;
import com.example.mapper.BpmnMapper;
import com.example.service.BpmnDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/14 15:23
 */
@Deprecated
@Service
public class BpmnDownloadServiceImpl extends ServiceImpl<BpmnMapper, BpmnModelEntity> implements BpmnDownloadService {
    @Autowired
    private BpmnMapper bpmnMapper;

    @Override
    public boolean getAndSaveBpmnModel(String model_key) {
        OutputStream outputStream = null;
        try {
            List<BpmnModelEntity> bpmnModelEntity = bpmnMapper.getModelInfo(model_key);
            File file = new File(FileSystemConstant.BPMN_FILE_LOCATION+model_key+".bpmn20.xml");
            if (file.createNewFile()) {
                if (file.exists()) {
                    System.out.println("file is create successfully");
                }
            }
            outputStream = new FileOutputStream(file);
            System.out.println("内容: "+bpmnModelEntity);

            if (bpmnModelEntity.get(0).getThumbnail().length != 0) {
                outputStream.write(bpmnModelEntity.get(0).getThumbnail());
            }
            outputStream.close();

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
