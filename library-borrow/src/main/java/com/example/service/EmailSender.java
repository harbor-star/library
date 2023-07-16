package com.example.service;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailSender {
    void sendEmail(String content);
    void sendHtmlEmail(String content) throws IOException, MessagingException;
    void sendAttachmentMail(String conent) throws MessagingException;
}
