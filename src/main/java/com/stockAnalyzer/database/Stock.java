package com.stockAnalyzer.database;

import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

public class Stock {

    final public Quote quote;

    private int id;
    public int getId() {
        return id;
    }

    private String symbol;
    public String getSymbol() {

        return symbol;
    }


    public Stock(int id,String symbol) {
        this.symbol = symbol;
        this.id = id;

        final IEXTradingClient iexTradingClient = IEXTradingClient.create();
        quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
                .withSymbol(symbol)
                .build());
    }

}
