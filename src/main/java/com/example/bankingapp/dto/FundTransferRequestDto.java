package com.example.bankingapp.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferRequestDto {
private Long fromAccountId;
private Long toAccountId;
private double amount;
}
