package com.example.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingapp.dto.AccountDto;
import com.example.bankingapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long>{
	List<Account> findByBalanceBetween(Long startBalance,Long endBalance);
	
}
