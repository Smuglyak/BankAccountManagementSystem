/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankaccountmanagementsystem;

import com.mycompany.bankaccountmanagementsystem.controller.ClientController;
import junit.framework.Assert;
import org.junit.Test;

public class ClientControllerTest {
    
    
    private ClientController clientController = new ClientController();
    
    @Test
    public void createClientInfo_insertValidData_shouldReturnTrue() {
        
        boolean result = clientController.create("First", "Last", "UniqueId", "Sample Address");
        
        Assert.assertEquals(true, result);
    }
    
    @Test
    public void createClientInfo_insertInvalidData_shouldReturnFalse() {
        boolean result = clientController.create("", "", "", "");
        
        Assert.assertEquals(false, result);
    }
}
