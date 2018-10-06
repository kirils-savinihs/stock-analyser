package Database;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompanyStockDataTest {


    @Test
    void constructors() {
        CompanyStockData testObj = new CompanyStockData(
                BigDecimal.valueOf(1),//PE <
                "Financial Services",
                BigDecimal.valueOf(2),//PriceToBook <
                BigDecimal.valueOf(3),//PriceToSales <
                BigDecimal.valueOf(4),//DividendYield >
                BigDecimal.valueOf(5),//ReturnOnEquity >
                BigDecimal.valueOf(6),//ReturnOnAssets >
                BigDecimal.valueOf(7),//ProfitMargin >
                BigDecimal.valueOf(8),//TtmEPS
                BigDecimal.valueOf(9),//Debt <
                BigDecimal.valueOf(10),//currentAssets
                BigDecimal.valueOf(11),//currentDebt
                BigDecimal.valueOf(12),//totalDebt
                BigDecimal.valueOf(13),//shareHolderEquity
                BigDecimal.valueOf(14),//cashFlow
                BigDecimal.valueOf(15),//PriceTOCashFlow
                BigDecimal.valueOf(16),//DebtToEquity
                BigDecimal.valueOf(17),//Liquidity <
                BigDecimal.valueOf(18),//LatestPrice
                19, "TEST", "TestCompany");

        assertEquals(BigDecimal.valueOf(1), testObj.PeRatio);
        assertTrue("Financial Services".equals(testObj.Sector));
        assertEquals(BigDecimal.valueOf(2), testObj.PriceToBook);
        assertEquals(BigDecimal.valueOf(3), testObj.PriceToSales);
        assertEquals(BigDecimal.valueOf(4), testObj.DividendYield);
        assertEquals(BigDecimal.valueOf(5), testObj.ReturnOnEquity);
        assertEquals(BigDecimal.valueOf(6), testObj.ReturnOnAssets);
        assertEquals(BigDecimal.valueOf(7), testObj.ProfitMargin);
        assertEquals(BigDecimal.valueOf(8), testObj.TtmEPS);
        assertEquals(BigDecimal.valueOf(9), testObj.Debt);
        assertEquals(BigDecimal.valueOf(10), testObj.currentAssets);
        assertEquals(BigDecimal.valueOf(11), testObj.currentDebt);
        assertEquals(BigDecimal.valueOf(12), testObj.totalDebt);
        assertEquals(BigDecimal.valueOf(13), testObj.shareHolderEquity);
        assertEquals(BigDecimal.valueOf(14), testObj.cashFlow);
        assertEquals(BigDecimal.valueOf(18), testObj.PriceToCashFlow);
        assertEquals(BigDecimal.valueOf(17), testObj.DebtToEquity);
        assertEquals(BigDecimal.valueOf(15), testObj.Liquidity);
        assertEquals(BigDecimal.valueOf(16), testObj.LatestPrice);
        assertEquals(19, testObj.id);
        assertEquals("TEST", testObj.symbol);
        assertEquals("TestCompany", testObj.CompName);


        testObj = new CompanyStockData("INTC");


    }



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