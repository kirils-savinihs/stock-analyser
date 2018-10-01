package com.stockAnalyzer.database;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import pl.zankowski.iextrading4j.api.stocks.*;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.CompanyRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.FinancialsRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.KeyStatsRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

import java.math.BigDecimal;

public class CompanyStockData implements Comparable<CompanyStockData> {

    private Quote quote;
    private Company company;
    private KeyStats keystats;
    private Financial financials;
    public final BigDecimal PeRatio;//less is better
    public final String Sector;
    public final BigDecimal PriceToBook;//less is better
    public final BigDecimal PriceToSales;//less is better
    public final BigDecimal DividendYield;//more is better
    public final BigDecimal ReturnOnEquity;//more is better
    public final BigDecimal ReturnOnAssets;//more is better
    public final BigDecimal ProfitMargin;//more is better
    public final BigDecimal TtmEPS;
    public final BigDecimal Debt; //more is worse
    public final BigDecimal currentAssets;
    public final BigDecimal currentDebt;
    public final BigDecimal totalDebt;
    public final BigDecimal shareHolderEquity;
    public final BigDecimal cashFlow;
    public final BigDecimal PriceToCashFlow;
    public final BigDecimal DebtToEquity;
    public final BigDecimal Liquidity;//more is better
    public final BigDecimal LatestPrice;


    public final int id;
    public final String symbol;


    public CompanyStockData(BigDecimal peRatio,
                            String sector,
                            BigDecimal priceToBook,
                            BigDecimal priceToSales,
                            BigDecimal dividendYield,
                            BigDecimal returnOnEquity,
                            BigDecimal returnOnAssets,
                            BigDecimal profitMargin,
                            BigDecimal ttmEPS,
                            BigDecimal debt,
                            BigDecimal currentAssets,
                            BigDecimal currentDebt,
                            BigDecimal totalDebt,
                            BigDecimal shareHolderEquity,
                            BigDecimal cashFlow,
                            BigDecimal Liquidity,
                            BigDecimal LatestPrice,
                            BigDecimal DebtToEquity,
                            BigDecimal PriceToCashFlow,
                            int id,
                            String symbol) {
        this.PeRatio = peRatio;
        this.Sector = sector;
        this.PriceToBook = priceToBook;
        this.PriceToSales = priceToSales;
        this.DividendYield = dividendYield;
        this.ReturnOnEquity = returnOnEquity;
        this.ReturnOnAssets = returnOnAssets;
        this.ProfitMargin = profitMargin;
        this.TtmEPS = ttmEPS;
        this.Debt = debt;
        this.currentAssets = currentAssets;
        this.currentDebt = currentDebt;
        this.totalDebt = totalDebt;
        this.shareHolderEquity = shareHolderEquity;
        this.cashFlow = cashFlow;
        this.id = id;
        this.symbol = symbol;
        this.DebtToEquity = DebtToEquity;
        this.LatestPrice = LatestPrice;
        this.PriceToCashFlow = PriceToCashFlow;
        this.Liquidity = Liquidity;
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
                .withSymbol(symbol)
                .build());

        financials = iexTradingClient.executeRequest(new FinancialsRequestBuilder()
                .withSymbol(symbol)
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

        if (totalDebt == null || shareHolderEquity == null)
            this.DebtToEquity = null;
        else
            this.DebtToEquity = totalDebt.divide(shareHolderEquity, 4, BigDecimal.ROUND_HALF_UP);

        this.LatestPrice = quote.getLatestPrice();


        if (this.LatestPrice == null || this.cashFlow == null)
            this.PriceToCashFlow = null;
        else
            this.PriceToCashFlow = this.LatestPrice.divide(this.cashFlow.divide(keystats.getSharesOutstanding(), 2, BigDecimal.ROUND_HALF_UP), 2, BigDecimal.ROUND_HALF_UP);

        if (this.currentAssets == null || this.currentDebt == null)
            this.Liquidity = null;
        else
            this.Liquidity = currentAssets.divide(currentDebt, 2, BigDecimal.ROUND_HALF_UP);


    }

    @Override
    public String toString() {
        return "CompanyStockData{" +
                "\n PeRatio=" + PeRatio +
                "\n Sector=" + Sector +
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
                "\n PriceToCashFlow=" + PriceToCashFlow +
                "\n DebtToEquity=" + DebtToEquity +
                "\n Liquidity=" + Liquidity +
                "\n LatestPrice=" + LatestPrice +
                "\n id=" + id +
                "\n symbol=" + symbol +
                "}\n";
    }


    @Override
    public int compareTo(CompanyStockData other) {
        // return >0 if this is better , return <0 if this is worse


        double[] compPercentages = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        double division = 0;

        try {
            //PeRation: less is better
            division = this.PeRatio.divide(other.PeRatio, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (division < 1)
                compPercentages[0] = (1 - division);
            else
                compPercentages[0] = (division - 1) * -1;
        } catch (NullPointerException e) {
        } catch (ArithmeticException e) {
        }


        //PriceToBook: less is better
        try {
            division = this.PriceToBook.divide(other.PriceToBook, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (division < 1)
                compPercentages[1] = (1 - division);
            else
                compPercentages[1] = (division - 1) * -1;
        } catch (NullPointerException e) {
        } catch (ArithmeticException e) {
        }


        try {
            //PriceToSales: less is better
            division = this.PriceToSales.divide(other.PriceToSales, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (division < 1)
                compPercentages[2] = (1 - division);
            else
                compPercentages[2] = (division - 1) * -1;
        } catch (NullPointerException e) {
        } catch (ArithmeticException e) {
        }


        //DividendYield: more is better
        try {
            division = this.DividendYield.divide(other.DividendYield, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (division < 1)
                compPercentages[3] = (1 - division) * -1;
            else
                compPercentages[3] = (division - 1);
        } catch (NullPointerException e) {
        } catch (ArithmeticException e) {
        }

        //ReturnOnEquity: more is better

        try {
            division = this.ReturnOnEquity.divide(other.ReturnOnEquity, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (division < 1)
                compPercentages[4] = (1 - division) * -1;
            else
                compPercentages[4] = (division - 1);
        } catch (NullPointerException e) {
        } catch (ArithmeticException e) {
        }

        //ReturnOnAssets: more is better
        try {
            division = this.ReturnOnAssets.divide(other.ReturnOnAssets, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (division < 1)
                compPercentages[5] = (1 - division) * -1;
            else
                compPercentages[5] = (division - 1);
        } catch (NullPointerException e) {
        } catch (ArithmeticException e) {
        }


        //ProfitMargin: more is better
        try {
            division = this.ProfitMargin.divide(other.ProfitMargin, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (division < 1)
                compPercentages[6] = (1 - division) * -1;
            else
                compPercentages[6] = (division - 1);
        } catch (NullPointerException e) {
        } catch (ArithmeticException e) {
        }

        //Debt: less is better
        try {
            division = this.Debt.divide(other.Debt, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (division < 1)
                compPercentages[0] = (1 - division);
            else
                compPercentages[0] = (division - 1) * -1;
        } catch (NullPointerException e) {
        } catch (ArithmeticException e) {
        }

        //Liquidity: more is better
        try {
            division = this.Liquidity.divide(other.Liquidity, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
            if (division < 1)
                compPercentages[6] = (1 - division) * -1;
            else
                compPercentages[6] = (division - 1);

        } catch (NullPointerException e) {
        } catch (ArithmeticException e) {
        }

        double sum = 0;

        compPercentages[0] *= 0.18;
        compPercentages[0] *= 0.18;
        compPercentages[0] *= 0.18;
        compPercentages[0] *= 0.1;
        compPercentages[0] *= 0.1;
        compPercentages[0] *= 0.1;
        compPercentages[0] *= 0.05;
        compPercentages[0] *= 0.05;
        compPercentages[0] *= 0.05;

        for (double i : compPercentages)
            sum += i;

        System.out.println(sum);


        if (sum > 0)
            return 1;
        else if (sum < 0)
            return -1;
        else
            return 0;

    }
}
