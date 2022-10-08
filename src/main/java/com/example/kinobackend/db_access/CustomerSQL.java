package com.example.kinobackend.db_access;

import com.example.kinobackend.responses.Customer;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public final class CustomerSQL extends MySqlConnector{

    public Customer[] getCustomerData(){
        ArrayList<Customer> data = new ArrayList<Customer>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customer");

            while(rs.next()){
                data.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9),rs.getString(10), rs.getString(11)));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return data.toArray(new Customer[0]);
    }
}