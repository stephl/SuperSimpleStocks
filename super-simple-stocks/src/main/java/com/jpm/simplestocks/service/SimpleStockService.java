package com.jpm.simplestocks.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jpm.simplestocks.dataaccess.DataAccessProvider;
import com.jpm.simplestocks.dataaccess.InMemoryDataAccessProvider;
import com.jpm.simplestocks.model.Stock;
import com.jpm.simplestocks.model.Trade;
import com.jpm.simplestocks.util.StockUtils;

public class SimpleStockService implements StockService {

	DataAccessProvider dataProvider = new InMemoryDataAccessProvider();

	public boolean addStock(Stock stock) {
		if (stock == null) {
			throw new IllegalArgumentException("Invalid stock provided.");
		}
		return dataProvider.addStock(stock);
	}

	public Stock getStock(String symbol) {
		return dataProvider.getStock(symbol);
	}

	public Collection<Stock> getStocks() {
		return dataProvider.getStocks();
	}

	public boolean recordTrade(Trade trade) {
		if (trade == null) {
			throw new IllegalArgumentException("Invalid trade provided.");
		}
		return dataProvider.recordTrade(trade);
	}

	public double calculateStockPrice(String stockSymbol) {
		if (stockSymbol == null || stockSymbol.length() == 0) {
			throw new IllegalArgumentException("Invalid stock symbol provided.");
		}

		Collection<Trade> trades = dataProvider.getTrades(stockSymbol);
		return calculateVolumeWeightedStockPrice(trades);
	}

	public double calculateAllShareIndex() {
		Map<String, List<Trade>> allTrades = dataProvider.getAllTrades();
		if (allTrades.size() == 0) {
			return 0;
		}

		List<Double> stockPrices = new ArrayList<Double>();
		for (List<Trade> trades : allTrades.values()) {
			double price = calculateVolumeWeightedStockPrice(trades);
			stockPrices.add(price);
		}

		return StockUtils.geometricMean(stockPrices);
	}

	protected double calculateVolumeWeightedStockPrice(Collection<Trade> trades) {
		if (trades == null) {
			return 0;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -15);
		Date windowStartDate = calendar.getTime();
		int tradeValue = 0;
		int totalTradeCount = 0;

		for (Trade trade : trades) {
			if (trade.getTransactionTime().after(windowStartDate)) {
				tradeValue += (trade.getQuantity() * trade.getTradePrice());
				totalTradeCount += trade.getQuantity();
			}
		}

		if (tradeValue == 0 || totalTradeCount == 0) {
			return 0;
		}

		double result = StockUtils.divideUsingBigDecimal(tradeValue, totalTradeCount);
		return result;

	}
}
