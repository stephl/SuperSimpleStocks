package com.jpm.simpestocks.dataaccess;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.jpm.simplestocks.model.Stock;
import com.jpm.simplestocks.model.Trade;

/**
 * Interface for accessing the Simple Stock database
 */
public interface DataAccessProvider {

	public boolean addStock(Stock stock);

	public boolean updateStock(Stock stock);

	public Collection<Stock> getStocks();

	public boolean recordTrade(Trade trade);

	public Collection<Trade> getTrades(String stockSymbol);

	public Map<String, List<Trade>> getAllTrades();
}
