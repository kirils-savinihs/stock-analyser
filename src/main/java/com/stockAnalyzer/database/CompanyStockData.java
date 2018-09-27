package com.stockAnalyzer.database;

import pl.zankowski.iextrading4j.api.stocks.*;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.CompanyRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.FinancialsRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.KeyStatsRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

import java.math.BigDecimal;

public class CompanyStockData {

    final public Quote quote;
    final public Company company;
    final public KeyStats keystats;
    final public Financial financials;
    public final BigDecimal PeRatio;
    public final String Sector;
    public final BigDecimal PriceToBook;
    public final BigDecimal PriceToSales;
    public final BigDecimal DividendYield;
    public final BigDecimal ReturnOnEquity;
    public final BigDecimal ReturnOnAssets;
    public final BigDecimal ProfitMargin;
    public final BigDecimal TtmEPS;
    public final BigDecimal Debt;
    public final BigDecimal currentAssets;
    public final BigDecimal currentDebt;
    public final BigDecimal totalDebt;
    public final BigDecimal shareHolderEquity;
    public final BigDecimal cashFlow;


    private int id;

    public int getId() {
        return id;
    }

    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public CompanyStockData(String symbol) {
        this(0, symbol);
    }


    public CompanyStockData(int id, String symbol) {
        this.symbol = symbol;
        this.id = id;

        final IEXTradingClient iexTradingClient = IEXTradingClient.create();
        quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
                .withSymbol(symbol)
                .build());


        company = iexTradingClient.executeRequest(new CompanyRequestBuilder()
                .withSymbol(symbol)
                .build());

        keystats = iexTradingClient.executeRequest(new KeyStatsRequestBuilder()
                .withSymbol("AAPL")
                .build());

        financials = iexTradingClient.executeRequest(new FinancialsRequestBuilder()
                .withSymbol("AAPL")
                .build()).getFinancials().get(0);


        this.PeRatio = quote.getPeRatio();
        this.Sector = company.getSector();
        this.PriceToBook = keystats.getPriceToBook();
        this.PriceToSales = keystats.getPriceToSales();
        this.DividendYield = keystats.getDividendYield();
        this.ReturnOnEquity = keystats.getReturnOnEquity();
        this.ReturnOnAssets = keystats.getReturnOnAssets();
        this.ProfitMargin = keystats.getProfitMargin();
        this.TtmEPS = keystats.getTtmEPS();
        this.Debt = keystats.getDebt();
        this.currentAssets = financials.getCurrentAssets();
        this.currentDebt = financials.getCurrentDebt();
        this.totalDebt = financials.getTotalDebt();
        this.shareHolderEquity = financials.getShareholderEquity();
        this.cashFlow = financials.getCashFlow();


    }

    @Override
    public String toString() {
        return "CompanyStockData{" +
                "\nPeRatio=" + PeRatio +
                "\n Sector='" + Sector + '\'' +
                "\n PriceToBook=" + PriceToBook +
                "\n PriceToSales=" + PriceToSales +
                "\n DividendYield=" + DividendYield +
                "\n ReturnOnEquity=" + ReturnOnEquity +
                "\n ReturnOnAssets=" + ReturnOnAssets +
                "\n ProfitMargin=" + ProfitMargin +
                "\n TtmEPS=" + TtmEPS +
                "\n Debt=" + Debt +
                "\n currentAssets=" + currentAssets +
                "\n currentDebt=" + currentDebt +
                "\n totalDebt=" + totalDebt +
                "\n shareHolderEquity=" + shareHolderEquity +
                "\n cashFlow=" + cashFlow +
                "\n id=" + id +
                "\n symbol='" + symbol + '\'' +
                '}';
    }
}
