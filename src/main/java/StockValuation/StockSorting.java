package StockValuation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.util.SocketUtils;

import com.stockAnalyzer.database.*;

import afu.org.checkerframework.checker.units.qual.s;
import antlr.collections.List;

public class StockSorting {
	static final String financials = "Financial Services";
	static final String utilities = "Utilities";
	static final String consumers = "Consumer Defensive";

	String[] sectors = { financials, utilities, consumers };

	enum ratio {
		PE, PB, PS, PC, DIVYLD, ROE, ROA, PM, LIQ
	};

	DatabaseManager database;
	ArrayList<CompanyStockData> companyList;

	public StockSorting() {
		this.database = new DatabaseManager();
		this.companyList = database.getAll();
	}

	public ArrayList<CompanyStockData> sectorSorting(String sectorName) {
		ArrayList<CompanyStockData> sectorList = new ArrayList<CompanyStockData>();
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
	/*
	 * public static Comparator<CompanyStockData> Liq = new
	 * Comparator<CompanyStockData>() {
	 * 
	 * @Override public int compare(CompanyStockData o1, CompanyStockData o2) {
	 * return o2.PriceToSales.compareTo(o1.PriceToSales); } };
	 */

	public ArrayList<CompanyStockData> getBy(ratio Ratio, ArrayList<CompanyStockData> prevList) {
		ArrayList<CompanyStockData> sectorList = prevList;
		/*
		 * if( o1.PeRatio.signum() < 0) { i = comlist.size();
		 * 
		 * } if( o2.PeRatio.signum() < 0) o2.PeRatio = null; if((o1.PeRatio != null &&
		 * o1.PeRatio.signum() > 0) && (o2.PeRatio != null && o2.PeRatio.signum() > 0))
		 */
		// switch

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
		/*
		 * case PC: Collections.sort(sectorList, Ps); break;
		 */
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
		/*
		 * case LIQ: Collections.sort(sectorList, Liq); break;
		 */

		}
		for (int i = 0; i < 2; i++) { // change to 5
			sectorList.remove(sectorList.size() - 1);
		}

		return sectorList;

	}

	public ArrayList<CompanyStockData> getFinancialServices() {
		return sectorSorting(financials);
	}

	public ArrayList<CompanyStockData> getUtilities() {
		return sectorSorting(utilities);
	}

	public ArrayList<CompanyStockData> getConsumers() {
		return sectorSorting(consumers);
	}

	public ArrayList<CompanyStockData> sort(String ratio) {

		return companyList;

	}

	public ArrayList<CompanyStockData> finalFinancialSorting() {
		ArrayList<CompanyStockData> finSector = getFinancialServices();
		ArrayList<CompanyStockData> sortedPE = getBy(ratio.PE, finSector);
		ArrayList<CompanyStockData> sortedPB = getBy(ratio.PB, sortedPE);
		ArrayList<CompanyStockData> sortedPS = getBy(ratio.PS, sortedPB);
		ArrayList<CompanyStockData> sortedDY = getBy(ratio.DIVYLD, sortedPS);
		ArrayList<CompanyStockData> sortedRoE = getBy(ratio.ROE, sortedDY);
		ArrayList<CompanyStockData> sortedRoA = getBy(ratio.ROA, sortedRoE);
		ArrayList<CompanyStockData> sortedPM = getBy(ratio.PM, sortedRoA);
		// ArrayList<CompanyStockData> sortedLiq = getBy(ratio.LIQ, sortedPM);
		return sortedPM;
	}

	public ArrayList<CompanyStockData> finalUtilitiesSorting() {
		ArrayList<CompanyStockData> utilSector = getUtilities();
		ArrayList<CompanyStockData> sortedPE = getBy(ratio.PE, utilSector);
		ArrayList<CompanyStockData> sortedPB = getBy(ratio.PB, sortedPE);
		ArrayList<CompanyStockData> sortedPS = getBy(ratio.PS, sortedPB);
		ArrayList<CompanyStockData> sortedDY = getBy(ratio.DIVYLD, sortedPS);
		ArrayList<CompanyStockData> sortedRoE = getBy(ratio.ROE, sortedDY);
		ArrayList<CompanyStockData> sortedRoA = getBy(ratio.ROA, sortedRoE);
		ArrayList<CompanyStockData> sortedPM = getBy(ratio.PM, sortedRoA);
		// ArrayList<CompanyStockData> sortedLiq = getBy(ratio.LIQ, sortedPM);
		return sortedPM;
	}

	public ArrayList<CompanyStockData> finalConsumersSorting() {
		ArrayList<CompanyStockData> consumSector = getConsumers();
		ArrayList<CompanyStockData> sortedPE = getBy(ratio.PE, consumSector);
		ArrayList<CompanyStockData> sortedPB = getBy(ratio.PB, sortedPE);
		ArrayList<CompanyStockData> sortedPS = getBy(ratio.PS, sortedPB);
		ArrayList<CompanyStockData> sortedDY = getBy(ratio.DIVYLD, sortedPS);
		ArrayList<CompanyStockData> sortedRoE = getBy(ratio.ROE, sortedDY);
		ArrayList<CompanyStockData> sortedRoA = getBy(ratio.ROA, sortedRoE);
		ArrayList<CompanyStockData> sortedPM = getBy(ratio.PM, sortedRoA);
		// ArrayList<CompanyStockData> sortedLiq = getBy(ratio.LIQ, sortedPM);
		return sortedPM;
	}

	public static void main(String[] args) {
		StockSorting ss = new StockSorting();

		try { ss.database.resetDatabase();
		System.out.println("Adding companies to database");
		ss.database.add("AES", "LNT", "AEE", "AEP", "AWK", "CNP", "CMS", "ED", "D", "DTE", "DUK", "EIX", "ETR", "EVRG",
				"ES", "EXC", "FE", "NEE", "NI", "NRG", "PCG", "PNW", "PPL", "PEG", 
				"AMG", "AFL", "ALL", "AXP", "AIG", "AMP", "AON", "AJG", "AIZ", "BAC", "BK", "BBT", "BRK.B", "BLK",
				"BHF", "COF", "CBOE", "SCHW", "CB", // Financials
				"MO", "ADM", "BF.B", "CPB", "CHD", "CLX", "KO", "CL", "CAG", "STZ", "COST", "CAG", "STZ", "COST",
				"COTY", "EL", "GIS", "HSY", "HRL", "SJM", "GIS", "HSY", "HRL", "SJM" // Consumers
		);
		System.out.println(ss.database.getAll());
		System.out.println("------------------------------------------------------");
		System.out.println("Sorting companies by financials:  ");
		System.out.println(ss.getFinancialServices());
		System.out.println("Final financial sorting: ");
		for (CompanyStockData item : ss.finalFinancialSorting()) {
			System.out.println(item.symbol);
		}
		System.out.println("--------------------------------------------------");
		System.out.println("Sorting companies by financials: ");
		System.out.println(ss.getUtilities());
		System.out.println("Final utilities sorted: ");
		System.out.println(ss.getFinancialServices());
		for (CompanyStockData item1 : ss.finalUtilitiesSorting()) {
			System.out.println(item1.symbol);
		}
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
