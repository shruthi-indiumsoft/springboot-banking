package com.example.bankingapp.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Transaction {

@Column(name="account_id")
private Long accountId;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long transactionId;
private Date date;
private String type;
private double amount;
private double balanceAfter;
}
