package StockValuation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.util.SocketUtils;

import com.stockAnalyzer.database.*;

import afu.org.checkerframework.checker.units.qual.s;
import java.util.List;

public class StockSorting {
	static final String financials = "Financial Services";
	static final String utilities = "Utilities";
	static final String consumers = "Consumer Defensive";

	String[] sectors = { financials, utilities, consumers };

	enum ratio {
		PE, PB, PS, PC, DIVYLD, ROE, ROA, PM, LIQ
	};

	DatabaseManager database;
	List<CompanyStockData> companyList;

	public StockSorting() {
		this.database = new DatabaseManager("");
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
			// lambda (1, 2)  pE vieta
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

		return sectorList.subList(0, sectorList.size()-1);

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

		try { ss.database.resetDatabase();
		System.out.println("Adding companies to database");
		ss.database.add("AES", "LNT", "AEE", "AEP", "AWK", "CNP", "CMS", "ED", "D", "DTE", "DUK", "EIX", "ETR",
				"ES", "EXC", "NEE", "NI", "NRG", "PCG", "PNW", "PPL", "PEG", "SCG", "SRE", //Utilities
				"AMG", "AFL", "ALL", "AXP", "AIG", "AMP", "AON", "AJG", "AIZ", "BAC", "BK", "BBT", "BRK.B", "BLK",
				"BHF", "COF", "CBOE", "SCHW", "CB", // Financials
				"MO", "ADM", "BF.B", "CPB", "CHD", "CLX", "KO", "CL", "CAG", "STZ", "COST", "CAG", "STZ", "COST",
				"COTY", "EL", "GIS", "HSY", "HRL", "SJM", "GIS", "HSY", "HRL", "SJM" // Consumers
		);
		System.out.println(ss.database.getAll());
		System.out.println("------------------------------------------------------");
		System.out.println("Sorting companies by financials:  ");
		System.out.println(ss.getFinancialServices());
		System.out.println("------------------------------------------------------");
		System.out.println("Final financial sorting: ");
		System.out.println(ss.finalFinancialSorting());
		System.out.println("------------------------------------------------------");
		System.out.println("Sorting companies by utilities:  ");
		System.out.println(ss.getUtilities());
		System.out.println("------------------------------------------------------");
		System.out.println("Final utilities sorting: ");
		System.out.println(ss.finalUtilitiesSorting());
		System.out.println("------------------------------------------------------");
		System.out.println("Sorting companies by consumers:  ");
		System.out.println(ss.getConsumers());
		System.out.println("------------------------------------------------------");
		System.out.println("Final consumers sorting: ");
		System.out.println(ss.finalConsumersSorting());
		
		
		/*ArrayList<CompanyStockData> utilSector = ss.getUtilities();
		ArrayList<CompanyStockData> sortedPE = ss.getBy(ratio.PE, utilSector);
		ArrayList<CompanyStockData> sortedPB = ss.getBy(ratio.PB, sortedPE);
		ArrayList<CompanyStockData> sortedPS = ss.getBy(ratio.PS, sortedPB);
		ArrayList<CompanyStockData> sortedDY = ss.getBy(ratio.DIVYLD, sortedPS);
		ArrayList<CompanyStockData> sortedRoE = ss.getBy(ratio.ROE, sortedDY);
		ArrayList<CompanyStockData> sortedRoA = ss.getBy(ratio.ROA, sortedRoE);
		ArrayList<CompanyStockData> sortedPM = ss.getBy(ratio.PM, sortedRoA);
		System.out.println(sortedPM);*/
		
		//
		/*for (CompanyStockData item : ss.finalFinancialSorting()) {
			System.out.println(item.symbol);
		}
		System.out.println("--------------------------------------------------");
		System.out.println("Sorting companies by financials: ");
		System.out.println(ss.getUtilities());
		System.out.println("Final utilities sorted: ");
		System.out.println(ss.getFinancialServices());
		for (CompanyStockData item1 : ss.finalUtilitiesSorting()) {
			System.out.println(item1.symbol);
		}*/
		}
		catch (Exception e) {
			System.err.println(e);}
		
	finally {	
		ss.database.closeConnection();
		}
		/*System.out.println("--------------------------------------------------");
		System.out.println("Consumers sorted: ");
		for (CompanyStockData item2 : ss.finalConsumersSorting()) {
			System.out.println(item2.symbol);
		}*/

		// System.out.println(ss.getBy(ratio.PB, ss.getFinancialServices()));

	
}

}
