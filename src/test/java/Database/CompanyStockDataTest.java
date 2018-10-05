package Database;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyStockDataTest {


    @Test
    void toStringTest() {
        CompanyStockData testObj = new CompanyStockData(
                BigDecimal.valueOf(1),//PE <
                "Financial Services",
                BigDecimal.valueOf(1),//PriceToBook <
                BigDecimal.valueOf(1),//PriceToSales <
                BigDecimal.valueOf(1),//DividendYield >
                BigDecimal.valueOf(1),//ReturnOnEquity >
                BigDecimal.valueOf(1),//ReturnOnAssets >
                BigDecimal.valueOf(1),//ProfitMargin >
                BigDecimal.valueOf(1),//TtmEPS
                BigDecimal.valueOf(1),//Debt <
                BigDecimal.valueOf(1),//currentAssets
                BigDecimal.valueOf(1),//currentDebt
                BigDecimal.valueOf(1),//totalDebt
                BigDecimal.valueOf(1),//shareHolderEquity
                BigDecimal.valueOf(1),//cashFlow
                BigDecimal.valueOf(1),//PriceTOCashFlow
                BigDecimal.valueOf(1),//DebtToEquity
                BigDecimal.valueOf(1),//Liquidity <
                BigDecimal.valueOf(1),//LatestPrice
                1, "TEST1", "TestCompany");


        assertEquals(testObj.toString(), "TEST1, TestCompany, 1");


    }

    @Test
    void compareTo() {

        CompanyStockData testObj1 = new CompanyStockData(
                BigDecimal.valueOf(1),//PE <
                "Financial Services",
                BigDecimal.valueOf(1),//PriceToBook <
                BigDecimal.valueOf(1),//PriceToSales <
                BigDecimal.valueOf(2),//DividendYield >
                BigDecimal.valueOf(2),//ReturnOnEquity >
                BigDecimal.valueOf(2),//ReturnOnAssets >
                BigDecimal.valueOf(2),//ProfitMargin >
                BigDecimal.valueOf(1),//TtmEPS
                BigDecimal.valueOf(1),//Debt <
                BigDecimal.valueOf(1),//currentAssets
                BigDecimal.valueOf(1),//currentDebt
                BigDecimal.valueOf(1),//totalDebt
                BigDecimal.valueOf(1),//shareHolderEquity
                BigDecimal.valueOf(1),//cashFlow
                BigDecimal.valueOf(1),//PriceTOCashFlow
                BigDecimal.valueOf(1),//DebtToEquity
                BigDecimal.valueOf(1),//Liquidity <
                BigDecimal.valueOf(1),//LatestPrice
                1, "TEST1", "TestCompany1");

        CompanyStockData testObj2 = new CompanyStockData(
                BigDecimal.valueOf(2),
                "Financial Services",
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(2),
                1, "TEST2", "TestCompany2");


        assertEquals(testObj1.compareTo(testObj2), 1);
        assertEquals(testObj2.compareTo(testObj1), -1);




    }
}