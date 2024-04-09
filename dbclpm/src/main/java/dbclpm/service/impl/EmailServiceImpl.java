package dbclpm.service.impl;

import dbclpm.dto.EmailDetailDTO;
import dbclpm.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(EmailDetailDTO emailDetailDTO) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetailDTO.getRecipient());
            mailMessage.setText(emailDetailDTO.getMsgBody());
            mailMessage.setSubject(emailDetailDTO.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail sent successfully!!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while Sending Mail";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetailDTO emailDetailDTO) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailDetailDTO.getRecipient());
            mimeMessageHelper.setText(emailDetailDTO.getMsgBody());
            mimeMessageHelper.setSubject(emailDetailDTO.getSubject());

            FileSystemResource file = new FileSystemResource(new File(emailDetailDTO.getAttachment()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);

            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while Sending Mail";
        }
    }
}
