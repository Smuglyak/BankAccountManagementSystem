/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankaccountmanagementsystem.controller;

import com.mycompany.bankaccountmanagementsystem.model.Client;
import com.mycompany.bankaccountmanagementsystem.dbhelper.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClientController {

    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public boolean create(String firstName, String lastName, String identification, String address) {

        try {
            
            if(firstName.length() == 0 ||
                    lastName.length() == 0 ||
                    identification.length() == 0 ||
                    address.length() == 0
                    ) {
                return false;
            }

            ps = DbConnection.connect().prepareStatement(
                    "INSERT INTO Clients(FirstName, LastName, Identification, Address) "
                    + "VALUES(?,?,?,?);"
            );

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, identification);
            ps.setString(4, address);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(String firstName, String lastName, String identification, String address, int clientId) {

        try {

            ps = DbConnection.connect().prepareStatement(
                    "UPDATE Clients SET FirstName = ?, LastName = ?, Identification = ?, Address = ? WHERE ClientId = ?;"
            );

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, identification);
            ps.setString(4, address);
            ps.setInt(5, clientId);

            ps.execute();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Client findById(int clientId) {
        try {

            ps = DbConnection.connect().prepareStatement(
                    "SELECT * FROM Clients WHERE ClientId = ?;"
            );

            ps.setInt(1, clientId);

            rs = ps.executeQuery();

            if (rs.next()) {
                return new Client(
                        clientId,
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Identification"),
                        rs.getString("Address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
