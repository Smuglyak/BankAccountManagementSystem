/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankaccountmanagementsystem.model;

import java.time.LocalDate;

public class Account {
    
    private final int accountNumber;
    private final int clientId;
    private String accountType;
    private final LocalDate openDate;
    private int balance;
    private boolean isActive;

    public Account(int accountNumber, int clientId, String accountType, int balance, boolean isActive) {
        this.accountNumber = accountNumber;
        this.clientId = clientId;
        this.accountType = accountType;
        this.openDate = LocalDate.now();
        this.balance = balance;
        this.isActive = isActive;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public String getAccountType() {
        return accountType;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public void withdraw(int value) {
        if(this.balance - value < 0) {
            throw new IllegalArgumentException("Value that you are going to send exceeds your balance.");
        }
        
        this.balance -= value;
    }
    
    public void deposit(int value) {
        this.balance += value; 
    }
}
