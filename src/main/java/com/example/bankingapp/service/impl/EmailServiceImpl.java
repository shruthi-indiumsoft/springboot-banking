package com.example.bankingapp.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.bankingapp.dto.AccountTransactionsDto;
import com.example.bankingapp.entity.EmailDetails;
import com.example.bankingapp.service.AccountService;
import com.example.bankingapp.service.EmailService;
import com.example.bankingapp.service.TransactionService;



@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private AccountService accountService;

    @Override
    public String sendSimpleMail(EmailDetails details,Long id) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(accountService.getAccountById(id).getEmailId());
            mailMessage.setSubject(details.getSubject());

            AccountTransactionsDto accountDto=transactionService.getTransactionsByAccountId(id);
            
            details.setMsgBody(details.getMsgBody() + "\n\n" + accountDto);
            mailMessage.setText(details.getMsgBody());
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
