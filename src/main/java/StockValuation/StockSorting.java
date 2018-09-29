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
	
	String [] sectors = {financials, utilities, consumers};
	
	enum ratio{
		PE, PB, PS, DIVYLD, ROE, ROA, PM, LIQ
	};
	

	DatabaseManager database;
	ArrayList<CompanyStockData> companyList;
	
	public StockSorting() {
		this.database = new DatabaseManager();
		this.companyList = database.getAll();
	}

	
	public ArrayList<CompanyStockData> sectorSorting(String sectorName){
		
		
		ArrayList<CompanyStockData> sectorList = new ArrayList<CompanyStockData>();
		
		for(CompanyStockData item : companyList) {
			if(item.Sector.equals(sectorName)) 
				sectorList.add(item);
		
		}
		return sectorList;
		
	}
	
	
	public static Comparator<CompanyStockData> Pe = new Comparator<CompanyStockData>() {
		@Override
		public int compare(CompanyStockData o1, CompanyStockData o2) {
			return o1.PeRatio.compareTo(o2.PeRatio); // Ascending order
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
	public static Comparator<CompanyStockData> Liq = new Comparator<CompanyStockData>() {
		@Override
		public int compare(CompanyStockData o1, CompanyStockData o2) {
			return o2.PriceToSales.compareTo(o1.PriceToSales);
		}
	};
	
	public ArrayList<CompanyStockData> getBy(ratio Ratio, ArrayList<CompanyStockData> prevList){
		ArrayList<CompanyStockData> sectorList = prevList;
		//switch
		
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
		case LIQ:
			Collections.sort(sectorList, Liq);
		break;
		
		}
		for(int i = 0; i < 2; i++) { // change to 5 
			sectorList.remove(sectorList.size()-1);
		}
	
		return sectorList;
		
	}
		
	public ArrayList<CompanyStockData> getFinancialServices(){
		return sectorSorting(financials);
	}
	public ArrayList<CompanyStockData> getUtilities(){
		return sectorSorting(utilities);
	}
	public ArrayList<CompanyStockData> getConsumers(){
		return sectorSorting(consumers);
	}
	
	public ArrayList<CompanyStockData> sort(String ratio){
		
		
		return companyList;
		
	} 
	
	
	
	public  static void main(String[] args) {
		StockSorting ss = new StockSorting();
		
		 ss.database.resetDatabase();
		 ss.database.add(
				 /*"EL", "GIS", "HSY", "HRL", "SJM", 
	        		"K", "KMB", "KHC", "KR", "MKC", "TAP",
	        		"MKC", "AMG", "AFL", "ALL", "AIG",
	        		"ES", "EXC", "NRG", "PCG", "PPL",
	        		"L", "BRK.B", "ALL", "JEF", "ALL",*/
	        		"AIG", "CB", "CINF", "HIG", "PGR", 
	        		"TRV", "BBT", "CFG", "FITB", "HBAN",
	        		"KEY", "MTB", "PNC", "RF", "STI", "SIVB",
	        		"ZION", "RE", "PBCT");
		 System.out.println(ss.database.getAll());
		  System.out.println("----------------------------------------------------- ");
		  
		 System.out.println(ss.getFinancialServices());
		 System.out.println("-----------------------------------------------");
		 System.out.println("-----------------------------------------------");
		 
		 System.out.println(ss.getBy(ratio.PE, ss.getFinancialServices()));
		 
		 //System.out.println(ss.getBy(ratio.PB, ss.getFinancialServices()));
		
	        ss.database.closeConnection();
	}
}
