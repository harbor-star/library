package com.example.service.impl;

import com.example.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/13 21:22
 */

@Service
public class EmailSenderImpl implements EmailSender {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("CHENGFEN.WEB@qq.com");
        simpleMailMessage.setTo("CHENGFEN.WEB@qq.com");
        simpleMailMessage.setSubject("TEST THE SEND MAIL");
        simpleMailMessage.setText("TEST SEND MAIL CONTENT");
        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendHtmlEmail(String content) throws IOException, MessagingException {
        String s = readString("C:\\Users\\Lenovo\\Desktop\\文件夹\\html\\baidu.html");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("CHENGFEN.WEB@qq.com");
        mimeMessageHelper.setTo("CHENGFEN.WEB@qq.com");
        mimeMessage.setSubject("HTML MAIL TEST");
        mimeMessageHelper.setText(s, true);
        mailSender.send(mimeMessage);

    }

    @Override
    public void sendAttachmentMail(String content) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("CHENGFEN.WEB@qq.com");
        mimeMessageHelper.setTo("CHENGFEN.WEB@qq.com");
        mimeMessageHelper.setSubject("SEND ATTACHMENT MAIL");
        mimeMessageHelper.setText("PLEASE CHECK THE ATTACHMENT");
        File file = new File("C:\\Users\\Lenovo\\Desktop\\文件夹\\html\\attachment.png");
        mimeMessageHelper.addAttachment("attachment.png", file);
        mailSender.send(mimeMessage);
    }

    private String readString(String filename) throws IOException {
        FileInputStream inputStream = new FileInputStream(filename);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String temp = "";
        StringBuilder stringBuffer = new StringBuilder(temp);

        while ( (temp = bufferedReader.readLine()) != null) {
            stringBuffer.append(temp);
            stringBuffer.append("\n");
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        return stringBuffer.toString();
    }
}
