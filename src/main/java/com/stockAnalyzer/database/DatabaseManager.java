package com.stockAnalyzer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    public Connection con;

    public DatabaseManager() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/?autoReconnect=true&useSSL=false", "root", "");
            con.setAutoCommit(false);
        } catch (SQLException e) {

            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
                    "Make sure mysql server is running\n" +
                    "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            con.commit();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Finds stock by id
     *
     * @param id stock id in database
     * @return Stock object
     */
    public Stock findStock(int id) {
        Stock stock = null;
        try {
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from stock_database.stocks where id=" + id);
            rs.next();
            stock = new Stock(id, rs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }

    /**
     * Finds stock by symbol example: "INTC" for Intel Corporation
     * @param symbol stock symbol
     * @return Stock object or null if sqlexception occurs
     */

    public Stock findStock(String symbol)
    {
        Stock stock = null;
        try {
            java.sql.Statement st = con.createStatement();
            System.out.println("select * from stock_database.stocks where symbol=\""+symbol+"\";");
            ResultSet rs = st.executeQuery("select * from stock_database.stocks where symbol=\""+symbol+"\"");
            rs.next();
            stock = new Stock(rs.getInt(1), rs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }



    public static void main(String[] args) {
        //For testing purposes only
        DatabaseManager db = new DatabaseManager();


        Stock st2 = db.findStock("INTC");


        System.out.println(st2.getId()+st2.getSymbol());



    }


}
