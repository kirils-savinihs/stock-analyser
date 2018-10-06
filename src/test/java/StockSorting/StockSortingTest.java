package StockSorting;

import Database.CompanyStockData;
import StockValuation.StockSorting;
import StockValuation.StockSorting.ratio;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StockSortingTest {

    public static List<CompanyStockData> manualySortedList = new ArrayList<CompanyStockData>();
    public static List<CompanyStockData> actualResults = new ArrayList<CompanyStockData>();
    List<CompanyStockData> expectedGetBy = new ArrayList<CompanyStockData>();
    StockSorting stockSorter = new StockSorting();

    CompanyStockData testobj1 = new CompanyStockData(BigDecimal.valueOf(2), "Utilities", BigDecimal.valueOf(7),
            BigDecimal.valueOf(1), BigDecimal.valueOf(23), BigDecimal.valueOf(98), BigDecimal.valueOf(100),
            BigDecimal.valueOf(56), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0),
            BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0),
            BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), 0,
            "Utilities" + Integer.valueOf(0).toString(), "UtilCompany");
    CompanyStockData testobj2 = new CompanyStockData(BigDecimal.valueOf(1), "Financial Services", BigDecimal.valueOf(4),
            BigDecimal.valueOf(32), BigDecimal.valueOf(87), BigDecimal.valueOf(45), BigDecimal.valueOf(48),
            BigDecimal.valueOf(45), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0),
            BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0),
            BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), 0,
            "Financials" + Integer.valueOf(0).toString(), "FinCompany");
    CompanyStockData testobj3 = new CompanyStockData(BigDecimal.valueOf(4), "Consumer Defensive", BigDecimal.valueOf(2),
            BigDecimal.valueOf(9), BigDecimal.valueOf(5), BigDecimal.valueOf(4), BigDecimal.valueOf(65),
            BigDecimal.valueOf(78), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0),
            BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0),
            BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), 0,
            "Consumers" + Integer.valueOf(0).toString(), "ConsCompany");

    @BeforeAll
    public void setUp() throws Exception {

    }

    @Test
    public void testSectorSorting() {
        actualResults.clear();
        actualResults.add(testobj1);
        actualResults.add(testobj2);
        actualResults.add(testobj3);
        stockSorter.companyList = actualResults;
        List<CompanyStockData> actualSortedResults;
        actualSortedResults = stockSorter.sectorSorting("Financial Services");
        assertEquals("sectorSorting() error for financials size.", 1l, actualSortedResults.size());
        actualSortedResults = stockSorter.sectorSorting("Utilities");
        assertEquals("sectorSorting() error for utilities size.", 1l, actualSortedResults.size());
        actualSortedResults = stockSorter.sectorSorting("Consumer Defensive");
        assertEquals("sectorSorting() error for consumers size.", 1l, actualSortedResults.size());
    }

    @Test
    public void testGetBy() {
        manualySortedList.add(testobj1);
        manualySortedList.add(testobj2);
        manualySortedList.add(testobj3);

        expectedGetBy.add(testobj2);
        actualResults = stockSorter.getBy(ratio.PE, manualySortedList);
        actualResults = actualResults.subList(0, 1);
        assertEquals("getBy(PE) error.", expectedGetBy, actualResults);

        expectedGetBy.clear();
        expectedGetBy.add(testobj3);
        actualResults = stockSorter.getBy(ratio.PB, manualySortedList).subList(0, 1);
        assertEquals("getBy(PB) error.", expectedGetBy, actualResults);

        expectedGetBy.clear();
        expectedGetBy.add(testobj1);
        actualResults = stockSorter.getBy(ratio.PS, manualySortedList).subList(0, 1);
        assertEquals("getBy(PS) error.", expectedGetBy, actualResults);

        expectedGetBy.clear();
        expectedGetBy.add(testobj2);
        actualResults = stockSorter.getBy(ratio.DIVYLD, manualySortedList).subList(0, 1);
        assertEquals("getBy(DIVYLD) error.", expectedGetBy, actualResults);

        expectedGetBy.clear();
        expectedGetBy.add(testobj1);
        actualResults = stockSorter.getBy(ratio.ROE, manualySortedList).subList(0, 1);
        assertEquals("getBy(ROE) error.", expectedGetBy, actualResults);

        expectedGetBy.clear();
        expectedGetBy.add(testobj1);
        actualResults = stockSorter.getBy(ratio.ROA, manualySortedList).subList(0, 1);
        assertEquals("getBy(ROA) error.", expectedGetBy, actualResults);

        expectedGetBy.clear();
        expectedGetBy.add(testobj3);
        actualResults = stockSorter.getBy(ratio.PM, manualySortedList).subList(0, 1);
        assertEquals("getBy(PM) error.", expectedGetBy, actualResults);

    }


    @Test
    public void testFinalFinancialSorting() {
        actualResults.clear();
        for (int i = 0; i < 19; i++) {
            actualResults.add(new CompanyStockData(BigDecimal.valueOf(i),
                    "Financial Services",
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    0,
                    "Utilities" + Integer.valueOf(i).toString(),
                    "UtilTest"));
        }
        actualResults = stockSorter.finalFinancialSorting();
        assertEquals("sectorSorting() error for final financials size.", 5l, actualResults.size());

    }

    @Test
    public void testFinalUtilitiesSorting() {
        actualResults.clear();
        for (int i = 0; i < 19; i++) {
            actualResults.add(new CompanyStockData(BigDecimal.valueOf(i),
                    "Utilities",
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    0,
                    "Utilities" + Integer.valueOf(i).toString(),
                    "UtilTest"));
        }
        actualResults = stockSorter.finalUtilitiesSorting();
        assertEquals("sectorSorting() error for final utilities size.", 5l, actualResults.size());


    }

    @Test
    public void testFinalConsumersSorting() {
        actualResults.clear();
        for (int i = 0; i < 19; i++) {
            actualResults.add(new CompanyStockData(BigDecimal.valueOf(i),
                    "Consumer Defensive",
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(i),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(0),
                    0,
                    "Consumers" + Integer.valueOf(i).toString(),
                    "ConsumTest"));
        }
        actualResults = stockSorter.finalConsumersSorting();
        assertEquals("sectorSorting() error for final consumers size.", 5l, actualResults.size());


    }
}

