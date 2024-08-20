package com.example.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapp.entity.EmailDetails;
import com.example.bankingapp.service.EmailService;


@RestController
public class EmailController {


	@Autowired
    private EmailService emailService;

    @PostMapping("/sendMail/{id}")
    public String sendMail(@PathVariable Long id,@RequestBody EmailDetails details) {
        return emailService.sendSimpleMail(details,id);
    }
}
