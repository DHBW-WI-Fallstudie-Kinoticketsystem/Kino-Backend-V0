package com.example.kinobackend.db_access;

import com.example.kinobackend.responses.Customer;
import com.example.kinobackend.responses.Employee;

import java.sql.ResultSet;
import java.sql.Statement;

public class UserSQL extends MySqlConnector {

    public Object getUserFromLoginData(String mailAdress, String password) {
        Object User = null;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employees where MailAddress = " + putStringIntoApostrophe(mailAdress) + " and Password = " + putStringIntoApostrophe(password));
            if (rs.next() == true) {
                return User = new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
           }else{
                rs = stmt.executeQuery("select * from customers where MailAddress = " + putStringIntoApostrophe(mailAdress) + " and Password = " + putStringIntoApostrophe(password));
            if (rs.next() == true){
                return User = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), 1, rs.getString(8),rs.getString(9), rs.getString(10));
            }
            }
               return "No User Found";
        } catch (Exception e) {
            System.out.println(e);
            return "Error Accessing Data";
        }
    }
}
