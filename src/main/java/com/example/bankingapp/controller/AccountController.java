package com.example.bankingapp.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.dto.FundTransferRequestDto;
import com.example.bankingapp.dto.FundTransferResponseDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.exception.ErrorDetails;
import com.example.bankingapp.exception.ResourceNotFoundException;
import com.example.bankingapp.service.AccountService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	
	@PostMapping	
	public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto account){
		AccountDto savedAccount=accountService.createAccount(account);
		return new ResponseEntity<AccountDto>(savedAccount,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
		AccountDto  accountDto =accountService.getAccountById(id) ;
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String, Double> request){
		  Double amount = request.get("amount");
		AccountDto accountDto =accountService.deposit(id,amount);
		 return ResponseEntity.ok(accountDto);
		  
	}
	
	 // Withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
                                               @RequestBody Map<String, Double> request){

        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Get All Accounts REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // Delete Account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully!");
    }
	
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotfoundException(ResourceNotFoundException exception,
//    		WebRequest webRequest){
//    	ErrorDetails errorDetails=new ErrorDetails(
//    			LocalDateTime.now(),
//    			exception.getMessage(),
//    			webRequest.getDescription(false),
//    			"USER_NOT_FOUND" );  			
//		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//    	
//    }
    
    //Fund transfer api
    @PostMapping("/fundTransfer")
    public ResponseEntity<FundTransferResponseDto> transferAmount(@RequestBody FundTransferRequestDto transferRequest) {
    	FundTransferResponseDto fundTransfer=accountService.transferAmount(transferRequest);
    	return new ResponseEntity<FundTransferResponseDto>(fundTransfer,HttpStatus.CREATED);
	
    }
    
    @GetMapping("/{startBalance}/{endBalance}")
    public ResponseEntity<List<AccountDto>> getByBalanceRange(@PathVariable Long startBalance,@PathVariable Long endBalance){
		List<AccountDto> accounts=accountService.getByBalanceRange(startBalance, endBalance);
		return  ResponseEntity.ok(accounts);
    	
    }
    
    

}
