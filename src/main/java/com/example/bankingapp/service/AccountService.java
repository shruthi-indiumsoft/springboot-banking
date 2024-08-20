package com.example.bankingapp.service;

import java.util.List;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.dto.FundTransferRequestDto;
import com.example.bankingapp.dto.FundTransferResponseDto;
import com.example.bankingapp.entity.Account;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	AccountDto getAccountById(Long id);
	AccountDto deposit(Long id,double amount);
	AccountDto withdraw(Long id, double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
    FundTransferResponseDto transferAmount(FundTransferRequestDto transferRequest);
    List<AccountDto> getByBalanceRange(Long startBalance,Long endBalance);
    

}
