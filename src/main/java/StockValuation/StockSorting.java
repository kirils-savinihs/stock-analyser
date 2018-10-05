package StockValuation;

import Database.CompanyStockData;
import Database.DatabaseManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StockSorting {
	static final String financials = "Financial Services";
	static final String utilities = "Utilities";
	static final String consumers = "Consumer Defensive";

	String[] sectors = { financials, utilities, consumers };

	enum ratio {
		PE, PB, PS, PC, DIVYLD, ROE, ROA, PM
	};

	DatabaseManager database;
	List<CompanyStockData> companyList;

	public StockSorting() {
		this.database = new DatabaseManager("00000000");
		this.companyList = database.getAll();
	}

	public List<CompanyStockData> sectorSorting(String sectorName) {
		List<CompanyStockData> sectorList = new ArrayList<CompanyStockData>();
		for (CompanyStockData item : companyList) {
			if (item.Sector.equals(sectorName))
				sectorList.add(item);

		}
		return sectorList;

	}

	public static Comparator<CompanyStockData> Pe = new Comparator<CompanyStockData>() {
		@Override
		public int compare(CompanyStockData o1, CompanyStockData o2) {
			return o1.PeRatio.compareTo(o2.PeRatio);
		}
	};
	public static Comparator<CompanyStockData> Pb = new Comparator<CompanyStockData>() {
		@Override
		public int compare(CompanyStockData o1, CompanyStockData o2) {
			return o1.PriceToBook.compareTo(o2.PriceToBook);
		}
	};
	public static Comparator<CompanyStockData> Ps = new Comparator<CompanyStockData>() {
		@Override
		public int compare(CompanyStockData o1, CompanyStockData o2) {
			return o1.PriceToSales.compareTo(o2.PriceToSales);
		}
	};
	public static Comparator<CompanyStockData> Pc = new Comparator<CompanyStockData>() {
		@Override
		public int compare(CompanyStockData o1, CompanyStockData o2) {
			return o1.PriceToCashFlow.compareTo(o2.PriceToCashFlow);
		}
	};
	public static Comparator<CompanyStockData> DivYld = new Comparator<CompanyStockData>() {
		@Override
		public int compare(CompanyStockData o1, CompanyStockData o2) {
			return o2.PriceToSales.compareTo(o1.PriceToSales);
		}
	};
	public static Comparator<CompanyStockData> RoE = new Comparator<CompanyStockData>() {
		@Override
		public int compare(CompanyStockData o1, CompanyStockData o2) {
			return o2.PriceToSales.compareTo(o1.PriceToSales);
		}
	};

	public static Comparator<CompanyStockData> RoA = new Comparator<CompanyStockData>() {
		@Override
		public int compare(CompanyStockData o1, CompanyStockData o2) {
			return o2.PriceToSales.compareTo(o1.PriceToSales);
		}
	};

	public static Comparator<CompanyStockData> ProfitMargin = new Comparator<CompanyStockData>() {
		@Override
		public int compare(CompanyStockData o1, CompanyStockData o2) {
			return o2.PriceToSales.compareTo(o1.PriceToSales);
		}
	};


	public List<CompanyStockData> getBy(ratio Ratio, List<CompanyStockData> prevList) {
		List<CompanyStockData> sectorList = prevList;
	

		switch (Ratio) {
		case PE:
			Collections.sort(sectorList, Pe);
			break;
		case PB:
			Collections.sort(sectorList, Pb);
			break;
		case PS:
			Collections.sort(sectorList, Ps);
			break;
		case DIVYLD:
			Collections.sort(sectorList, DivYld);
			break;
		case ROE:
			Collections.sort(sectorList, RoE);
			break;
		case ROA:
			Collections.sort(sectorList, RoA);
			break;
		case PM:
			Collections.sort(sectorList, ProfitMargin);
			break;
		}

        return sectorList.subList(0, sectorList.size() - 2);

	}

	public List<CompanyStockData> getFinancialServices() {
		return sectorSorting(financials);
	}

	public List<CompanyStockData> getUtilities() {
		return sectorSorting(utilities);
	}

	public List<CompanyStockData> getConsumers() {
		return sectorSorting(consumers);
	}

	public List<CompanyStockData> sort(String ratio) {

		return companyList;

	}

	public List<CompanyStockData> finalFinancialSorting() {
		List<CompanyStockData> finalSort = getFinancialServices();
		finalSort = getBy(ratio.PE, finalSort);
		finalSort = getBy(ratio.PB, finalSort);
		finalSort = getBy(ratio.PS, finalSort);
		finalSort = getBy(ratio.DIVYLD, finalSort);
		finalSort = getBy(ratio.ROE, finalSort);
		finalSort = getBy(ratio.ROA, finalSort);
		finalSort = getBy(ratio.PM, finalSort);
		return finalSort;
	}

	public List<CompanyStockData> finalUtilitiesSorting() {
		List<CompanyStockData> finalSort = getUtilities();
		finalSort = getBy(ratio.PE, finalSort);
		finalSort = getBy(ratio.PB, finalSort);
		finalSort = getBy(ratio.PS, finalSort);
		finalSort = getBy(ratio.DIVYLD, finalSort);
		finalSort = getBy(ratio.ROE, finalSort);
		finalSort = getBy(ratio.ROA, finalSort);
		finalSort = getBy(ratio.PM, finalSort);
		return finalSort;
	
	}

	public List<CompanyStockData> finalConsumersSorting() {
		List<CompanyStockData> finalSort = getConsumers();
		finalSort = getBy(ratio.PE, finalSort);
		finalSort = getBy(ratio.PB, finalSort);
		finalSort = getBy(ratio.PS, finalSort);
		finalSort = getBy(ratio.DIVYLD, finalSort);
		finalSort = getBy(ratio.ROE, finalSort);
		finalSort = getBy(ratio.ROA, finalSort);
		finalSort = getBy(ratio.PM, finalSort);
		return finalSort;
	}

	public static void main(String[] args) {
		StockSorting ss = new StockSorting();

        try {
            ss.database.resetDatabase();
		System.out.println("Adding companies to database");
            ss.database.add(
                    "AES", "LNT", "AEE", "AEP", "AWK", "CNP", "CMS", "ED", "D", "DTE", "DUK", "EIX", "ETR",
                "ES", "EXC", "NEE", "NI", "NRG", "PCG", //Utilities

				"AMG", "AFL", "ALL", "AXP", "AIG", "AMP", "AON", "AJG", "AIZ", "BAC", "BK", "BBT", "BRK.B", "BLK",
				"BHF", "COF", "CBOE", "SCHW", "CB", // Financials

                "MO", "ADM", "BF.B", "CPB", "CHD", "CLX", "KO", "CL", "CAG", "STZ", "CAG", "STZ", "COST",
                "COTY", "EL", "GIS", "HSY", "HRL", "GIS" // Consumers
		);
            //System.out.println(ss.database.getAll());
		System.out.println("------------------------------------------------------");
		System.out.println("Sorting companies by financials:  ");
		System.out.println(ss.getFinancialServices());
		System.out.println("------------------------------------------------------");
		System.out.println("Final financial sorting: ");
            //System.out.println(ss.finalFinancialSorting());
            for (CompanyStockData data : ss.finalFinancialSorting()) {
                System.out.println(data.symbol);
            }


            System.out.println("\n" + ss.finalFinancialSorting().size() + "\n");
		System.out.println("------------------------------------------------------");
		System.out.println("Sorting companies by utilities:  ");
		System.out.println(ss.getUtilities());

		System.out.println("------------------------------------------------------");
		System.out.println("Final utilities sorting: ");
		System.out.println(ss.finalUtilitiesSorting());
//            System.out.println("\n" + ss.finalUtilitiesSorting().size() + "\n");
            for (CompanyStockData data : ss.finalUtilitiesSorting()) {
                System.out.println(data.symbol);
            }
            System.out.println("\n" + ss.finalUtilitiesSorting().size() + "\n");
		System.out.println("------------------------------------------------------");
		System.out.println("Sorting companies by consumers:  ");
		System.out.println(ss.getConsumers());
		System.out.println("------------------------------------------------------");
		System.out.println("Final consumers sorting: ");
//		System.out.println(ss.finalConsumersSorting());
            for (CompanyStockData data : ss.finalConsumersSorting()) {
                System.out.println(data.symbol);
            }
            System.out.println("\n" + ss.finalConsumersSorting().size() + "\n");
		
	
		}
		catch (Exception e) {
			System.err.println(e);}
		
	finally {	
		ss.database.closeConnection();
		}
		

	
}

}
