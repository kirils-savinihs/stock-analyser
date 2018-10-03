package com.stockAnalyzer.database;


import pl.zankowski.iextrading4j.api.stocks.Company;
import pl.zankowski.iextrading4j.api.stocks.Financial;
import pl.zankowski.iextrading4j.api.stocks.KeyStats;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.CompanyRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.FinancialsRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.KeyStatsRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

import java.math.BigDecimal;
import java.util.Comparator;

public class CompanyStockData implements Comparable<CompanyStockData> {


    //All the variables:
    private Quote quote;
    private Company company;
    private KeyStats keystats;
    private Financial financials;
    public final BigDecimal PeRatio;//less is better
    public final String Sector;
    public final String CompName;
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


    /**
     * Comparator for Collections.sort();
     */
    public static final Comparator<CompanyStockData> comparator = new Comparator<CompanyStockData>() {
        @Override
        public int compare(CompanyStockData o1, CompanyStockData o2) {
            return o2.compareTo(o1);
        }
    };


    /**
     * Constructor for row from database
     *
     * @param peRatio
     * @param sector
     * @param priceToBook
     * @param priceToSales
     * @param dividendYield
     * @param returnOnEquity
     * @param returnOnAssets
     * @param profitMargin
     * @param ttmEPS
     * @param debt
     * @param currentAssets
     * @param currentDebt
     * @param totalDebt
     * @param shareHolderEquity
     * @param cashFlow
     * @param Liquidity
     * @param LatestPrice
     * @param DebtToEquity
     * @param PriceToCashFlow
     * @param id
     * @param symbol
     * @param CompName
     */
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
                            String symbol,
                            String CompName) {
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
        this.CompName = CompName;
    }

    /**
     * CompanyStockData constructor using IEXTrading API data
     * @param symbol Company symbol
     */
    public CompanyStockData(String symbol) {
        this(0, symbol);
    }


    /**
     * CompanyStockData constructor using IEXTrading API data
     *
     * @param symbol Company symbol
     */
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

        this.CompName = company.getCompanyName();


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
                "\n CompName=" + CompName +
                "}\n";
    }


    /**
     * Compares two big decimals
     * @param first first variable
     * @param second second variable
     * @param moreIsBetter determines whether a bigger number is considered better
     * @return -1 if worse first is worse, 0 if same, +1 if first is better
     */
    private static double compOneStat(BigDecimal first, BigDecimal second, boolean moreIsBetter) {

        double sign;
        try {
            sign = first.compareTo(second);
            if (sign == 0)
                sign = 1;
        } catch (NullPointerException e) {
            return 0;
        }
        double division = 0;
        double percentage = 0;

        BigDecimal bigger;
        BigDecimal smaller;

        if (sign == -1) {
            bigger = second;
            smaller = first;
        } else {
            bigger = first;
            smaller = second;
        }

        try {
            division = bigger.divide(smaller, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (NullPointerException e) {

        } catch (ArithmeticException e) {

        }

        percentage = (division - 1) * sign;

        if (!moreIsBetter)
            percentage *= -1;


        return percentage;
    }

//    private static double compOneStat(BigDecimal first, BigDecimal second, boolean moreIsBetter) {
//        double division = 0;
//        double percentage = 0;
//
//
//        try {
//            division = first.divide(second, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
//        } catch (NullPointerException e) {
//
//        } catch (ArithmeticException e) {
//
//        }
//
//        percentage = Math.abs(division - 1);
//
//        if ((!moreIsBetter && division > 1) || (moreIsBetter && division < 1)) {
//            percentage *= -1;
//        }
//
//        return percentage;
//    }


    /**
     * Compares this object to another using all their variables
     * @param other CompanyStockData object that should be compared to
     * @return -1 if this is worse, 0 if same, +1 if this is better
     */
    @Override
    public int compareTo(CompanyStockData other) {
        // return >0 if this is better , return <0 if this is worse


        double[] compPercentages = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        compPercentages[0] = compOneStat(this.PeRatio, other.PeRatio, false);
        compPercentages[1] = compOneStat(this.PriceToBook, other.PriceToBook, false);
        compPercentages[2] = compOneStat(this.PriceToSales, other.PriceToSales, false);
        compPercentages[3] = compOneStat(this.DividendYield, other.DividendYield, true);
        compPercentages[4] = compOneStat(this.ReturnOnEquity, other.ReturnOnEquity, true);
        compPercentages[5] = compOneStat(this.ReturnOnAssets, other.ReturnOnAssets, true);
        compPercentages[6] = compOneStat(this.ProfitMargin, other.ProfitMargin, true);
        compPercentages[7] = compOneStat(this.Debt, other.Debt, false);
        compPercentages[8] = compOneStat(this.Liquidity, other.Liquidity, false);


        double sum = 0;


        //Weight of each stat
        compPercentages[0] *= 0.17;
        compPercentages[1] *= 0.16;
        compPercentages[2] *= 0.16;
        compPercentages[3] *= 0.11;
        compPercentages[4] *= 0.11;
        compPercentages[5] *= 0.11;
        compPercentages[6] *= 0.06;
        compPercentages[7] *= 0.06;
        compPercentages[8] *= 0.06;

        for (double i : compPercentages)
            sum += i;


        if (sum > 0)
            return 1;
        else if (sum < 0)
            return -1;
        else
            return 0;

    }

//    Testing code
//
//    public static void main(String[] args) {
//
//        CompanyStockData testObj1 = new CompanyStockData(
//                BigDecimal.valueOf(1),//PE <
//                "Financial Services",
//                BigDecimal.valueOf(1),//PriceToBook <
//                BigDecimal.valueOf(1),//PriceToSales <
//                BigDecimal.valueOf(2),//DividendYield >
//                BigDecimal.valueOf(2),//ReturnOnEquity >
//                BigDecimal.valueOf(2),//ReturnOnAssets >
//                BigDecimal.valueOf(2),//ProfitMargin >
//                BigDecimal.valueOf(1),//TtmEPS
//                BigDecimal.valueOf(1),//Debt <
//                BigDecimal.valueOf(1),//currentAssets
//                BigDecimal.valueOf(1),//currentDebt
//                BigDecimal.valueOf(1),//totalDebt
//                BigDecimal.valueOf(1),//shareHolderEquity
//                BigDecimal.valueOf(1),//cashFlow
//                BigDecimal.valueOf(1),//PriceTOCashFlow
//                BigDecimal.valueOf(1),//DebtToEquity
//                BigDecimal.valueOf(1),//Liquidity <
//                BigDecimal.valueOf(1),//LatestPrice
//                1,"TEST1");
//
//        CompanyStockData testObj2 = new CompanyStockData(
//                BigDecimal.valueOf(2),
//                "Financial Services",
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(1),
//                BigDecimal.valueOf(1),
//                BigDecimal.valueOf(1),
//                BigDecimal.valueOf(1),
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(2),
//                BigDecimal.valueOf(2),
//                1,"TEST2");
//
//
//        System.out.println(testObj1.compareTo(testObj2));
//
//        System.out.println(testObj2.compareTo(testObj1));
//
//        System.out.println(compOneStatExperimental(BigDecimal.valueOf(1),BigDecimal.valueOf(2),true)); //expect -1
//        System.out.println(compOneStatExperimental(BigDecimal.valueOf(2),BigDecimal.valueOf(1),false));//expect -1
//        System.out.println(compOneStatExperimental(BigDecimal.valueOf(2),BigDecimal.valueOf(1),true)); //expect 1
//        System.out.println(compOneStatExperimental(BigDecimal.valueOf(1),BigDecimal.valueOf(2),false));//expect 1
//
//    }
}
