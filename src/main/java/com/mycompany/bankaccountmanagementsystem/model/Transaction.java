/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankaccountmanagementsystem.model;

public class Transaction {
    
    private final int transactionId;
    private final int toAccountNumber;
    private final int fromAccountNumber;
    private final String transactionDetail;
    private final int value;

    public Transaction(int transactionId, int toAccountNumber, int fromAccountNumber, String transactionDetail, int value) {
        this.transactionId = transactionId;
        this.toAccountNumber = toAccountNumber;
        this.fromAccountNumber = fromAccountNumber;
        this.transactionDetail = transactionDetail;
        this.value = value;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getToAccountNumber() {
        return toAccountNumber;
    }

    public int getFromAccountNumber() {
        return fromAccountNumber;
    }

    public String getTransactionDetail() {
        return transactionDetail;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transactionId=" + transactionId + ", toAccountNumber=" + toAccountNumber + ", fromAccountNumber=" + fromAccountNumber + ", transactionDetail=" + transactionDetail + ", value=" + value + '}';
    }
}
