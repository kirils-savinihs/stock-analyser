package com.stockAnalyzer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import pl.zankowski.iextrading4j.*;

import java.io.IOException;
import java.math.BigDecimal;


public class stockAnalyzerApp {


    public static void main(String[] args) {

        final IEXTradingClient iexTradingClient = IEXTradingClient.create();
        final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
                .withSymbol("AAPL")
                .build());


        System.out.println(quote);


    }


}
