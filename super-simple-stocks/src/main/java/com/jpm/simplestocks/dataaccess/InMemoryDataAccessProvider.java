package com.jpm.simplestocks.dataaccess;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jpm.simplestocks.model.Stock;
import com.jpm.simplestocks.model.Trade;

/**
 * In memory implementation of data access provider.
 *
 */
public class InMemoryDataAccessProvider implements DataAccessProvider {

	private final Set<Stock> stocks = new HashSet<Stock>();
	private final Map<String, List<Trade>> trades = new HashMap<String, List<Trade>>();

	/**
	 * Adds a new stock type.
	 * 
	 * @return {@code true} If the stock is added {@code false} if the stock
	 *         already exists
	 * 
	 *         the same name already exists then this method
	 */
	public boolean addStock(Stock stock) {
		if (stock == null) {
			return false;
		}

		synchronized (stocks) {
			return stocks.add(stock);
		}
	}

	public Stock getStock(String symbol) {
		synchronized (stocks) {

			for (Stock s : stocks) {
				if (s.getSymbol().equals(symbol)) {
					return s;
				}
			}
		}
		return null;
	}

	/**
	 * Replaces the existing value of Stock with a new version.
	 * 
	 * @return {@code true} If the stock is updated {@code false} if the stock
	 *         does not already exist
	 *
	 */
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

	/**
	 * Gets the current known Stocks
	 * 
	 * @return A Collection containing a copy of the currently known Stocks
	 * 
	 */
	public Collection<Stock> getStocks() {
		return new HashSet<Stock>(stocks);
	}

	public boolean recordTrade(Trade trade) {
		if (trade == null) {
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

	/**
	 * Gets the current known Trades for a stock
	 * 
	 * @param a stock symbol String identifying a Stock object
	 * @return A Collection containing a copy of the currently known Trades
	 * 
	 */
	public Collection<Trade> getTrades(String stockSymbol) {
		List<Trade> tradesForStock = trades.get(stockSymbol);
		if(tradesForStock != null) {
			return new LinkedList<Trade>(tradesForStock);
		}
		
		return Collections.emptyList();
	}

	/**
	 * Return a copy of all trades.
	 */
	public Map<String, List<Trade>> getAllTrades() {
		return new HashMap<String, List<Trade>>(trades);
	}
}
