package com.stockAnalyzer.database;

import java.sql.*;

public class DatabaseManager {

    private Connection con;

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

    /**
     * Adds a new CompanyStockData object's fields to the mysql database
     *
     * @param symbol Company symbol e.g "INTC" for Intel Corporation
     */
    public void add(String symbol) {
        CompanyStockData data = new CompanyStockData(symbol);
        Statement st = null;
        try {
            st = con.createStatement();
            String sql = "INSERT INTO `stock_database`.`stocks`\n" +
                    "(`id`,\n" +
                    "`symbol`,\n" +
                    "`PeRatio`,\n" +
                    "`Sector`,\n" +
                    "`PriceToBook`,\n" +
                    "`PriceToSales`,\n" +
                    "`DividendYield`,\n" +
                    "`ReturnOnEquity`,\n" +
                    "`ReturnOnAssets`,\n" +
                    "`ProfitMargin`,\n" +
                    "`TtmEPS`,\n" +
                    "`Debt`,\n" +
                    "`currentAssets`,\n" +
                    "`currentDebt`,\n" +
                    "`totalDebt`,\n" +
                    "`shareHolderEquity`,\n" +
                    "`cashFlow`)\n" +
                    "VALUES\n" +
                    "(null,\n\'" +
                    data.getSymbol() + "\',\n" +
                    data.PeRatio + ",\n\'" +
                    data.Sector + "\',\n" +
                    data.PriceToBook + ",\n" +
                    data.PriceToSales + ",\n" +
                    data.DividendYield + ",\n" +
                    data.ReturnOnEquity + ",\n" +
                    data.ReturnOnAssets + ",\n" +
                    data.ProfitMargin + ",\n" +
                    data.TtmEPS + ",\n" +
                    data.Debt + ",\n" +
                    data.currentAssets + ",\n" +
                    data.currentDebt + ",\n" +
                    data.totalDebt + ",\n" +
                    data.shareHolderEquity + ",\n" +
                    data.cashFlow + ");\n";

            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * Resets the database by dropping and recreating everything
     */
    public void resetDatabase() {
        String[] sql = new String[5];

        sql[0] = "drop database if exists stock_database;";
        sql[1] = "create database stock_database;";
        sql[2] = "use stock_database;";
        sql[3] = "drop table if exists stocks;";
        sql[4] = "create table stocks (\n" +
                "id int (10) unsigned not null auto_increment,\n" +
                "symbol varchar(30),\n" +
                "PeRatio double,\n" +
                "Sector varchar(30),\n" +
                "PriceToBook  double,\n" +
                "PriceToSales double,\n" +
                "DividendYield\tdouble,\n" +
                "ReturnOnEquity double,\n" +
                "ReturnOnAssets double,\n" +
                "ProfitMargin double,\n" +
                "TtmEPS double,\n" +
                "Debt bigint,\n" +
                "currentAssets bigint, \n" +
                "currentDebt bigint,\n" +
                "totalDebt bigint,\n" +
                "shareHolderEquity bigint,\n" +
                "cashFlow bigint,\n" +
                "primary key (id)\n" +
                ");";
        try {
            Statement st = con.createStatement();
            for (String s : sql) {
                st.execute(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Closes the connection
     */
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
     * @return CompanyStockData object
     */
    public CompanyStockData findById(int id) {
        CompanyStockData companyStockData = null;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from stock_database.stocks where id=" + id);
            rs.next();
            companyStockData = new CompanyStockData(id, rs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyStockData;
    }

    /**
     * Finds stock by symbol example: "INTC" for Intel Corporation
     * @param symbol stock symbol
     * @return CompanyStockData object or null if sqlexception occurs
     */

    public CompanyStockData findBySymbol(String symbol) {
        CompanyStockData companyStockData = null;
        try {
            java.sql.Statement st = con.createStatement();
            System.out.println("select * from stock_database.stocks where symbol=\""+symbol+"\";");
            ResultSet rs = st.executeQuery("select * from stock_database.stocks where symbol=\""+symbol+"\"");
            rs.next();
            companyStockData = new CompanyStockData(rs.getInt(1), rs.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyStockData;
    }



    public static void main(String[] args) {
        //For testing purposes only

        DatabaseManager db = new DatabaseManager();
//        CompanyStockData st2 = db.findBySymbol("INTC");
//        System.out.println(st2.getId()+st2.getSymbol());
        db.resetDatabase();
        db.add("INTC");
        db.closeConnection();
    }



}
