package com.example.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferResponseDto {
	private String status;
	private Long transactionId;
	private double fromAccountBalance;
	private double toAccountBalance;
	

}
