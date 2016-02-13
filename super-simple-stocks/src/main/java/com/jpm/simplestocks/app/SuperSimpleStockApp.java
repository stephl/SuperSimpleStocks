package com.jpm.simplestocks.app;

import java.util.Date;
import java.util.Scanner;

import com.jpm.simplestocks.model.CommonStock;
import com.jpm.simplestocks.model.PreferredStock;
import com.jpm.simplestocks.model.Stock;
import com.jpm.simplestocks.model.Trade;
import com.jpm.simplestocks.service.SimpleStockService;
import com.jpm.simplestocks.service.StockService;

/**
 * A basic CLI interface onto the stock service
 *
 */
public class SuperSimpleStockApp {

	private static StockService service = new SimpleStockService();

	public static void main(String args[]) {

		setupStocks();
		System.out.println("---------------------------------------");
		System.out.println("Welcome to the Super Simple Stock App");
		System.out.println("---------------------------------------");
		printHelp();
		System.out.println("-------------------------------------");
		Scanner console = new Scanner(System.in);
		try {
			while (console.hasNext()) {
				String line = console.nextLine();
				if (line.startsWith("HELP")) {
					printHelp();
				} else if (line.startsWith("GET")) {
					printStocks();
				} else if (line.startsWith("PE")) {
					doPE(line);
				} else if (line.startsWith("YIELD")) {
					doYield(line);
				} else if (line.startsWith("BUY")) {
					doBuy(line);
				} else if (line.startsWith("SELL")) {
					doSell(line);
				} else if (line.startsWith("PRICE")) {
					doPrice(line);
				} else if (line.startsWith("INDEX")) {
					doIndex();
				} else if (line.startsWith("EXIT")) {
					System.exit(0);
				}
			}
		} finally {
			console.close();
		}
	}

	private static void setupStocks() {
		Stock tea = new CommonStock("TEA", 2, 100);
		Stock pop = new CommonStock("POP", 8, 110);
		Stock joe = new CommonStock("JOE", 3, 80);
		Stock gin = new PreferredStock("GIN", 8, 100, 2);
		Stock why = new PreferredStock("WHY", 4, 45, 2.5);
		Stock vod = new PreferredStock("VOD", 5, 72, 3);

		service.addStock(tea);
		service.addStock(pop);
		service.addStock(joe);
		service.addStock(gin);
		service.addStock(why);
		service.addStock(vod);
	}

	private static void printHelp() {
		System.out.println("Enter: ");
		System.out.println("HELP to view this help page");
		System.out.println("GET to view current stocks");
		System.out.println("PE <symbol> <price> to see the PE Ratio for the given stock and price ");
		System.out.println("YIELD <symbol> <price> to see the Dividend Yield for the given stock and price ");
		System.out.println("BUY <symbol> <quantity> <price> to buy ");
		System.out.println("SELL <symbol> <quantity> <price> to sell ");
		System.out.println("PRICE <symbol> to see the stock price for the given stock ");
		System.out.println("INDEX to see the All Share Index");
		System.out.println("EXIT to exit the system");
	}

	private static void printStocks() {
		System.out.println("Stocks currently available are:");
		for (Stock s : service.getStocks()) {
			StringBuffer sb = new StringBuffer();
			sb.append(s.getSymbol());
			sb.append(" ");
			sb.append("Last Dividend: ");
			sb.append(s.getLastDividend());
			sb.append(" ");
			if (s instanceof PreferredStock) {
				sb.append("Fixed Dividend: ");
				sb.append(((PreferredStock) s).getFixedDividendPercentage());
				sb.append(" ");
			}
			sb.append("Par: ");
			sb.append(s.getParValue());
			System.out.println(sb.toString());
		}

	}

	private static void doPE(String line) {
		String[] params = line.split(" ");
		if (params.length != 3) {
			printHelp();
			return;
		}
		String symbol = params[1];
		String price = params[2];
		Stock stock = service.getStock(symbol);
		if (stock != null) {
			double pe = stock.getPeRatio(Integer.parseInt(price));
			System.out.println("PE Ratio: " + pe);
		}
	}

	private static void doYield(String line) {
		String[] params = line.split(" ");
		if (params.length != 3) {
			printHelp();
			return;
		}
		String symbol = params[1];
		String price = params[2];
		Stock stock = service.getStock(symbol);
		if (stock != null) {
			double yield = stock.getDividendYield(Integer.parseInt(price));
			System.out.println("Yield: " + yield);
		}

	}

	private static void doBuy(String line) {
		String[] params = line.split(" ");
		if (params.length != 4) {
			printHelp();
			return;
		}
		String symbol = params[1];
		String quantity = params[2];
		String price = params[3];
		service.recordTrade(
				new Trade(symbol, Integer.parseInt(quantity), Integer.parseInt(price), new Date(), Trade.Type.BUY));
		System.out.println("BUY made for " + symbol);
	}

	private static void doSell(String line) {
		String[] params = line.split(" ");
		if (params.length != 4) {
			printHelp();
			return;
		}
		String symbol = params[1];
		String quantity = params[2];
		String price = params[3];
		service.recordTrade(
				new Trade(symbol, Integer.parseInt(quantity), Integer.parseInt(price), new Date(), Trade.Type.SELL));
		System.out.println("SELL made for " + symbol);
	}

	private static void doPrice(String line) {
		String[] params = line.split(" ");
		if (params.length != 2) {
			printHelp();
			return;
		}
		String symbol = params[1];
		double price = service.calculateStockPrice(symbol);
		System.out.println(symbol + " : " + price);

	}

	private static void doIndex() {
		double idx = service.calculateAllShareIndex();
		System.out.println("All share index: " + idx);

	}
}
