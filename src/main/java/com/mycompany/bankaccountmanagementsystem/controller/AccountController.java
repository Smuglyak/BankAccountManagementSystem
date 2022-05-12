/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankaccountmanagementsystem.controller;

import com.mycompany.bankaccountmanagementsystem.model.Account;
import com.mycompany.bankaccountmanagementsystem.model.Client;
import com.mycompany.bankaccountmanagementsystem.dbhelper.DbConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class AccountController {

    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private ClientController clientController = new ClientController();

    public boolean create(int accountNumber, int clientId, int balance, String accountType, boolean isActive) {

        try {
            
            if(accountNumber <= 0 || clientId <= 0 || balance <= 0 ||
                    accountType.length() == 0
                    ) {
                return false;
            }

            Client client = clientController.findById(clientId);

            if (client == null) {
                return false;
            }

            ps = DbConnection.connect().prepareStatement(
                    "INSERT INTO Accounts(AccountNumber, ClientId, AccountType, OpenDate, Balance, IsActive)"
                    + " VALUES(?,?,?,?,?,?);"
            );

            ps.setInt(1, accountNumber);
            ps.setInt(2, clientId);
            ps.setString(3, accountType);
            ps.setDate(4, Date.valueOf(LocalDate.now()));
            ps.setInt(5, balance);
            ps.setBoolean(6, isActive);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(int accountNumber, int clientId, int balance, String accountType, boolean isActive) {

        try {

            Client client = clientController.findById(clientId);

            if (client == null) {
                return false;
            }

            Account acc = findByAccountNumber(accountNumber);

            if (acc == null) {
                return false;
            }

            ps = DbConnection.connect().prepareStatement(
                    "UPDATE Accounts SET Balance = ?, AccountType = ?, IsActive = ? WHERE AccountNumber = ? AND ClientId = ?;"
            );

            ps.setInt(1, balance);
            ps.setString(2, accountType);
            ps.setBoolean(3, isActive);
            ps.setInt(4, accountNumber);
            ps.setInt(5, clientId);

            ps.execute();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account findByAccountNumber(int accountNumber) {
        try {

            ps = DbConnection.connect().prepareStatement(
                    "SELECT * FROM Accounts WHERE AccountNumber = ?"
            );

            ps.setInt(1, accountNumber);

            rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(
                        accountNumber,
                        rs.getInt("ClientId"),
                        rs.getString("AccountType"),
                        rs.getInt("Balance"),
                        rs.getBoolean("IsActive")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean deactivateAccount(int accountNumber) {
        try {

            Account acc = findByAccountNumber(accountNumber);

            if (acc == null) {
                return false;
            }

            ps = DbConnection.connect().prepareStatement(
                    "UPDATE Accounts SET IsActive = ? WHERE AccountNumber = ?;"
            );

            ps.setBoolean(1, false);
            ps.setInt(2, accountNumber);

            ps.execute();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBalance(int toAccountNumber, int fromAccountNumber, int value) {

        try {
            Account toAcc = findByAccountNumber(toAccountNumber);

            Account fromAcc = findByAccountNumber(fromAccountNumber);

            if (toAcc == null || fromAcc == null) {
                return false;
            }
            
            if(toAcc.getAccountNumber() == fromAcc.getAccountNumber()) {
                return false;
            }

            if(value <= 0) {
                return false;
            }
            
            toAcc.deposit(value);
            fromAcc.withdraw(value);

            PreparedStatement ps1 = DbConnection.connect().prepareStatement(
                    "UPDATE Accounts SET Balance = ? WHERE AccountNumber = ?;"
            );

            ps1.setInt(1, toAcc.getBalance());
            ps1.setInt(2, toAccountNumber);
            
            PreparedStatement ps2 = DbConnection.connect().prepareStatement(
                    "UPDATE Accounts SET Balance = ? WHERE AccountNumber = ?;"
            );
            
            ps2.setInt(1, fromAcc.getBalance());
            ps2.setInt(2, fromAccountNumber);
            
            return ps1.executeUpdate() > 0 && ps2.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
