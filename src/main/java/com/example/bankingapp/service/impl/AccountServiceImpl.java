package com.example.bankingapp.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.dto.FundTransferRequestDto;
import com.example.bankingapp.dto.FundTransferResponseDto;
import com.example.bankingapp.dto.TransactionDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.exception.ResourceNotFoundException;
import com.example.bankingapp.mapper.AccountMapper;
import com.example.bankingapp.mapper.TransactionMapper;
import com.example.bankingapp.repository.AccountRepository;
import com.example.bankingapp.repository.TransactionRepository;
import com.example.bankingapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
    private TransactionRepository transactionRepository;
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Account","Id",id));
		return AccountMapper.mapToAccountDto(account);
		
	}
	
	@Override
	public AccountDto deposit(Long id,double amount) {
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Account","id",id));
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		 TransactionDto transactionDto = new TransactionDto();
		    transactionDto.setDate(new Date(System.currentTimeMillis()));
//		    transactionDto.setTransactionId(1L);
		    transactionDto.setType("Deposit");
		    transactionDto.setAmount(amount);
		    transactionDto.setBalanceAfter(total);
		    transactionDto.setAccountId(id); // Set the associated account

		    // Map DTO to Transaction entity
		    Transaction transaction = TransactionMapper.mapToTransaction(transactionDto);

		    // Save the transaction and retrieve the saved instance (including transactionId)
		    Transaction savedTransaction = transactionRepository.save(transaction);

		    // Convert saved transaction to DTO to get transactionId
		    TransactionDto savedTransactionDto = TransactionMapper.mapToTransactionDto(savedTransaction);
		return AccountMapper.mapToAccountDto(savedAccount);
		
	}
	
	 @Override
	    public AccountDto withdraw(Long id, double amount) {

	        Account account = accountRepository
	                .findById(id)
	                .orElseThrow(() -> new RuntimeException("Account does not exists"));

	        if(account.getBalance() < amount){
	            throw new RuntimeException("Insufficient amount");
	        }

	        double total = account.getBalance() - amount;
	        account.setBalance(total);
	        Account savedAccount = accountRepository.save(account);

	        return AccountMapper.mapToAccountDto(savedAccount);
	    }

	    @Override
	    public List<AccountDto> getAllAccounts() {
	        List<Account> accounts = accountRepository.findAll();
	        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
	                .collect(Collectors.toList());
	    }

	    @Override
	    public void deleteAccount(Long id) {

	        Account account = accountRepository
	                .findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Account","id",id));

	        accountRepository.deleteById(id);
	    }

		@Override
		public FundTransferResponseDto transferAmount(FundTransferRequestDto transferRequest) {
			// TODO Auto-generated method stub
			  Account fromAccount = accountRepository
		                .findById(transferRequest.getFromAccountId())
		                .orElseThrow(() -> new RuntimeException("Account does not exists"));
			  
			  double totalAfterDebit = fromAccount.getBalance() - transferRequest.getAmount();
		        fromAccount.setBalance(totalAfterDebit);
		       accountRepository.save(fromAccount);

		        Account toAccount = accountRepository
		                .findById(transferRequest.getToAccountId())
		                .orElseThrow(() -> new RuntimeException("Account does not exists"));
			  
			  double totalAfterCredit = toAccount.getBalance() + transferRequest.getAmount();
		        toAccount.setBalance(totalAfterCredit);
		        accountRepository.save(toAccount);

		        
		        
			    TransactionDto fromtransactionDto = new TransactionDto();
			    fromtransactionDto.setDate(new Date(System.currentTimeMillis()));

			    fromtransactionDto.setType("Debit");
			    fromtransactionDto.setAmount(transferRequest.getAmount());
			    fromtransactionDto.setBalanceAfter(fromAccount.getBalance());
			    fromtransactionDto.setAccountId(transferRequest.getFromAccountId()); // Set the associated account

			    // Map DTO to Transaction entity
			    Transaction fromTransaction = TransactionMapper.mapToTransaction(fromtransactionDto);

			    // Save the transaction and retrieve the saved instance (including transactionId)
			    Transaction savedFromTransaction = transactionRepository.save(fromTransaction);

			    // Convert saved transaction to DTO to get transactionId
			    TransactionDto savedFromTransactionDto = TransactionMapper.mapToTransactionDto(savedFromTransaction);
			    
			    TransactionDto totransactionDto = new TransactionDto();
			    totransactionDto.setDate(new Date(System.currentTimeMillis()));

			    totransactionDto.setType("Credit");
			    totransactionDto.setAmount(transferRequest.getAmount());
			    totransactionDto.setBalanceAfter(toAccount.getBalance());
			    totransactionDto.setAccountId(transferRequest.getToAccountId()); // Set the associated account

			    // Map DTO to Transaction entity
			    Transaction toTransaction = TransactionMapper.mapToTransaction(totransactionDto);

			    // Save the transaction and retrieve the saved instance (including transactionId)
			    Transaction savedToTransaction = transactionRepository.save(toTransaction);

			    // Convert saved transaction to DTO to get transactionId
			    TransactionDto savedToTransactionDto = TransactionMapper.mapToTransactionDto(savedToTransaction);
			    
			    FundTransferResponseDto transferResponse=new FundTransferResponseDto();
			    transferResponse.setStatus("SUCCESS");
			    transferResponse.setToAccountBalance(fromAccount.getBalance());
			    transferResponse.setFromAccountBalance(toAccount.getBalance());
			    transferResponse.setTransactionId(1L);
			    
			    return transferResponse;
			
			
		}

		@Override
		public List<AccountDto> getByBalanceRange(Long startBalance, Long endBalance) {
			// TODO Auto-generated method stub
			List<Account> accounts=accountRepository.findByBalanceBetween(startBalance, endBalance);
			
			 return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
		                .collect(Collectors.toList());
			
		}
	
	
	
}
