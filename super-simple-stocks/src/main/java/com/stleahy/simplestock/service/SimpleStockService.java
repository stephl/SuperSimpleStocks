package com.stleahy.simplestock.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import com.jpm.simpestocks.dataaccess.DataAccessProvider;
import com.jpm.simpestocks.dataaccess.InMemoryDataAccessProvider;
import com.jpm.simplestocks.model.Stock;
import com.jpm.simplestocks.model.Trade;
import com.jpm.simplestocks.util.StockUtils;

public class SimpleStockService {

	DataAccessProvider dataProvider = new InMemoryDataAccessProvider();

	public boolean addStock(Stock stock) {
		if (stock == null) {
			throw new IllegalArgumentException("Invalid stock provided.");
		}
		return dataProvider.addStock(stock);
	}

	public boolean recordTrade(Trade trade) {
		if (trade == null) {
			throw new IllegalArgumentException("Invalid trade provided.");
		}
		return dataProvider.recordTrade(trade);
	}

	public double calculateVolumeWeightedStockPrice(String stockSymbol) {
		if (stockSymbol == null || stockSymbol.length() == 0) {
			throw new IllegalArgumentException("Invalid stock symbol provided.");
		}

		Collection<Trade> allTrades = dataProvider.getTrades(stockSymbol);
		if (allTrades == null) {
			return 0;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -15);
		Date windowStartDate = calendar.getTime();
		int tradeValue = 0;
		int totalTradeCount = 0;

		for (Trade trade : allTrades) {
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
