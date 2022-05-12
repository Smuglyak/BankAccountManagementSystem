/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankaccountmanagementsystem.controller;

import com.mycompany.bankaccountmanagementsystem.dbhelper.DbConnection;
import com.mycompany.bankaccountmanagementsystem.model.Transaction;
import com.mycompany.bankaccountmanagementsystem.controller.AccountController;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionController {

    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private AccountController accountController = new AccountController();

    public boolean create(int toAccountNumber, int fromAccountNumber, int value, String transactionDetail) {

        try {
            
            if(toAccountNumber <= 0 || fromAccountNumber <= 0 || value <= 0) {
                return false;
            }

            ps = DbConnection.connect().prepareStatement(
                    "INSERT INTO Transactions(ToAccountNumber, FromAccountNumber, TransactionDetail, Value)"
                    + " VALUES(?,?,?,?);"
            );

            ps.setInt(1, toAccountNumber);
            ps.setInt(2, fromAccountNumber);
            ps.setString(3, transactionDetail);
            ps.setInt(4, value);

            boolean result = accountController.updateBalance(toAccountNumber, fromAccountNumber, value);

            if (result) {
                return ps.executeUpdate() > 0;
            } else {
                return false;
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Transaction> getAll() {
        try {
            List<Transaction> transactions = new ArrayList<>();

            ps = DbConnection.connect().prepareStatement(
                    "SELECT * FROM Transactions;"
            );

            rs = ps.executeQuery();

            while (rs.next()) {
                
                transactions.add(new Transaction(
                        rs.getInt("TransactionId"),
                        rs.getInt("ToAccountNumber"),
                        rs.getInt("FromAccountNumber"),
                        rs.getString("TransactionDetail"),
                        rs.getInt("Value")
                ));
            }
            
            return transactions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
