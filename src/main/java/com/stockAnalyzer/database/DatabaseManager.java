package com.stockAnalyzer.database;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {

    private Connection con;

    /**
     * Create database manager
     *
     * @param mysqlpassword MySQL root password
     */
    public DatabaseManager(String mysqlpassword) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/?autoReconnect=true&useSSL=false", "root", mysqlpassword);
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
     * @param symbols Company symbol e.g "INTC" for Intel Corporation
     */
    public void add(String... symbols) { // ... takes any number of Strings
        for (String symbol : symbols) {
            System.out.println("Adding " + symbol);
            CompanyStockData data = new CompanyStockData(symbol);
            if (
                    data.PeRatio == null ||
                            data.PriceToBook == null ||
                            data.PriceToSales == null ||
                            data.DividendYield == null ||
                            data.ReturnOnEquity == null ||
                            data.ReturnOnAssets == null ||
                            data.ProfitMargin == null
            ) {
                System.out.println(data.symbol + " was not added, not all data is available from api");
                continue;
            }



            Statement st = null;
            try {
                st = con.createStatement();

                String sql = "INSERT INTO `stock_database`.`stocks`\n" +
                        "(`id`,\n" +
                        "`symbol`,\n" +
                        "`CompName`,\n" +
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
                        "`cashFlow`,\n" +
                        "`Liquidity`,\n" +
                        "`LatestPrice`,\n" +
                        "`DebtToEquity`,\n" +
                        "`PriceToCashFlow`)\n" +
                        "VALUES\n" +
                        "(null,\n\'" +
                        data.symbol + "\',\n\'" +
                        data.CompName + "\',\n\'" +
                        data.PeRatio + "\',\n\'" +
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
                        data.cashFlow + ",\n" +
                        data.Liquidity + ",\n" +
                        data.LatestPrice + ",\n" +
                        data.DebtToEquity + ",\n" +
                        data.PriceToCashFlow + ");\n";

                st.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

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
                "CompName varchar(100),\n" +
                "PeRatio double,\n" +
                "Sector varchar(30),\n" +
                "PriceToBook  double,\n" +
                "PriceToSales double,\n" +
                "DividendYield double,\n" +
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
                "Liquidity double,\n" +
                "LatestPrice double,\n" +
                "DebtToEquity double,\n" +
                "PriceToCashFlow double,\n" +
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

//    /**
//     * Finds stock by id
//     *
//     * @param id stock id in database
//     * @return CompanyStockData object
//     */
//    public CompanyStockData findById(int id) {
//        CompanyStockData companyStockData = null;
//        try {
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("select * from stock_database.stocks where id=" + id);
//            rs.next();
//            companyStockData = new CompanyStockData(id, rs.getString(2));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return companyStockData;
//    }

//    /**
//     * Finds stock by symbol example: "INTC" for Intel Corporation
//     * @param symbol stock symbol
//     * @return CompanyStockData object or null if sqlexception occurs
//     */
//
//    public CompanyStockData findBySymbol(String symbol) {
//        CompanyStockData companyStockData = null;
//        try {
//            java.sql.Statement st = con.createStatement();
//            System.out.println("select * from stock_database.stocks where symbol=\""+symbol+"\";");
//            ResultSet rs = st.executeQuery("select * from stock_database.stocks where symbol=\""+symbol+"\"");
//            rs.next();
//            companyStockData = new CompanyStockData(rs.getInt(1), rs.getString(2));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return companyStockData;
//    }
//
public ArrayList<CompanyStockData> getAll() {
    ArrayList<CompanyStockData> data = new ArrayList<CompanyStockData>();
    try {
        java.sql.Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from stock_database.stocks");


        while (rs.next()) {
            data.add(new CompanyStockData(
                    rs.getBigDecimal(4),
                    rs.getString(5),
                    rs.getBigDecimal(6),
                    rs.getBigDecimal(7),
                    rs.getBigDecimal(8),
                    rs.getBigDecimal(9),
                    rs.getBigDecimal(10),
                    rs.getBigDecimal(11),
                    rs.getBigDecimal(12),
                    rs.getBigDecimal(13),
                    rs.getBigDecimal(14),
                    rs.getBigDecimal(15),
                    rs.getBigDecimal(16),
                    rs.getBigDecimal(17),
                    rs.getBigDecimal(18),
                    rs.getBigDecimal(19),
                    rs.getBigDecimal(20),
                    rs.getBigDecimal(21),
                    rs.getBigDecimal(22),
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
            ));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return data;


}




}
