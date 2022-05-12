/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankaccountmanagementsystem.dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DbConnection extends JFrame {

    private static Connection con = null;

    public static Connection connect() {

        try {
            if (con == null) {
                Class.forName("org.sqlite.JDBC");
                
                con = DriverManager.getConnection("jdbc:sqlite:\\C:\\Users\\HP\\Desktop\\AllProjects\\FinalFinalFinalProject\\BankAccountManagementSystem\\src\\main\\java\\com\\mycompany\\bankaccountmanagementsystem\\dbhelper\\BankDB.db");
                System.out.println("Connected");
            }
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
}
