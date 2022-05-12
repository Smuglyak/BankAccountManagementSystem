/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankaccountmanagementsystem;

import com.mycompany.bankaccountmanagementsystem.controller.TransactionController;
import junit.framework.Assert;
import org.junit.Test;

public class TransactionControllerTest {
    
    private TransactionController transactionController = new TransactionController();
    
    @Test
    public void createTransaction_insertValidData_shouldReturnTrue() {
        
        boolean result = transactionController.create(3, 1, 200, "");
        
        Assert.assertEquals(true, result);
    }
    
    @Test
    public void createTransaction_insertInvalidData_shouldReturnFalse() {
        boolean result = transactionController.create(3, 1, -200, "");
        
        Assert.assertEquals(false, result);
    }
    
   
}
