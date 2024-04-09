package dbclpm.service;

import dbclpm.dto.EmailDetailDTO;

public interface EmailService {
    String sendSimpleMail(EmailDetailDTO emailDetailDTO);
    String sendMailWithAttachment(EmailDetailDTO emailDetailDTO);
}
