package com.jpm.simplestocks.service;

import java.util.Collection;

import com.jpm.simplestocks.model.Stock;
import com.jpm.simplestocks.model.Trade;

public interface StockService {

	boolean addStock(Stock stock);

	Stock getStock(String symbol);

	Collection<Stock> getStocks();

	boolean recordTrade(Trade trade);

	double calculateStockPrice(String stockSymbol);

	double calculateAllShareIndex();

}