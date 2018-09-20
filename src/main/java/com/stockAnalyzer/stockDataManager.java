package com.stockAnalyzer;


//Class that gets stock data from yahoo finance
// TODO: write the code

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

public class stockDataManager {

    public static void main(String[] args) {

        Stock stock = null;
        try {
            stock = YahooFinance.get("INTC");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal change = stock.getQuote().getChangeInPercent();
        BigDecimal peg = stock.getStats().getPeg();
        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

        stock.print();

    }
}
