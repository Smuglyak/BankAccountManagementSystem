/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankaccountmanagementsystem.controller;

import com.mycompany.bankaccountmanagementsystem.dbhelper.DbConnection;
import com.mycompany.bankaccountmanagementsystem.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserController {

    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public boolean login(String username, String password) {
        try {
            ps = DbConnection.connect().prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
            return user != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
