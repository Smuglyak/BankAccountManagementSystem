/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankaccountmanagementsystem.model;

public class Client {
    
    private final int clientId;
    private String firstName;
    private String lastName;
    private String identification;
    private String address;

    public Client(int clientId, String firstName, String lastName, String identification, String address) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identification = identification;
        this.address = address;
    }

    public int getClientId() {
        return clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public String getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
