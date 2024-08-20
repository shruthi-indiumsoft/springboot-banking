package com.example.bankingapp.entity;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Table(name = "accounts")
	@Entity
	public class Account {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "account_holder_name")
	    private String accountHolderName;
	    private double balance;
//	    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
//	    private List<TransactionHistory> transactions;
	    private Long phoneNumber;
	    private String emailId;

	}


