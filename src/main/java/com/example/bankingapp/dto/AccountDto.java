package com.example.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	 private Long id;
	    private String accountHolderName;
	    private double balance;
	    private Long phoneNumber;
	    private String emailId;
}
