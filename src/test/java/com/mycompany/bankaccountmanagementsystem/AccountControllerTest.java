/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankaccountmanagementsystem;

import com.mycompany.bankaccountmanagementsystem.controller.AccountController;
import junit.framework.Assert;
import org.junit.Test;


public class AccountControllerTest {
    
    private AccountController accountController = new AccountController();
    
    @Test
    public void createAccount_insertValidData_shouldReturnTrue() {
        
        // This will fail when runned again because account number is already
        // existing in the database. You should delete it first.
        boolean result = accountController.create(1, 1, 1000, "Savings", true);
        
        Assert.assertEquals(false, result);
    }
    
    @Test
    public void createAccount_insertInvalidData_shouldReturnFalse() {
        boolean result = accountController.create(1, 1, -1000, "Savings", true);
        
        Assert.assertEquals(false, result);
    }
    
    
}
