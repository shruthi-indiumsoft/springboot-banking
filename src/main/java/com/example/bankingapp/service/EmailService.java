package com.example.bankingapp.service;

import com.example.bankingapp.entity.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details,Long id);
}
