package com.jpm.simpestocks.dataaccess;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.jpm.simplestocks.model.Stock;
import com.jpm.simplestocks.model.Trade;

/**
 * In memory implementation of data access provider.
 *
 */
public class InMemoryDataAccessProvider implements DataAccessProvider {

	private final Set<Stock> stocks = new HashSet<Stock>();
	private final Map<String, List<Trade>> trades = new ConcurrentHashMap<String, List<Trade>>();

	public boolean addStock(Stock stock) {
		if (stock == null) {
			return false;
		}

		synchronized (stocks) {
			return stocks.add(stock);
		}
	}

	public boolean updateStock(Stock stock) {
		if (stock == null) {
			return false;
		}

		boolean result = false;
		synchronized (stocks) {
			if (stocks.contains(stock)) {
				stocks.remove(stock);
				result = stocks.add(stock);
			}
		}

		return result;
	}

	public Collection<Stock> getStocks() {
		return Collections.unmodifiableSet(stocks);
	}

	public boolean recordTrade(Trade trade) {
		if (trade == null || trade == null || trade.getStockSymbol().isEmpty()) {
			return false;
		}

		synchronized (trades) {
			List<Trade> tradesForStock = trades.get(trade.getStockSymbol());
			if (tradesForStock == null) {
				tradesForStock = new LinkedList<Trade>();
				trades.put(trade.getStockSymbol(), tradesForStock);
			}
			return tradesForStock.add(trade);
		}
	}

	public Collection<Trade> getTrades(String stockSymbol) {
		synchronized (trades) {
			return trades.get(stockSymbol);
		}
	}

	public Map<String, List<Trade>> getAllTrades() {
		return Collections.unmodifiableMap(trades);
	}
}
