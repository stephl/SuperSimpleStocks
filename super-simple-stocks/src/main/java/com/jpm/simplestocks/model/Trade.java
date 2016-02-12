package com.jpm.simplestocks.model;

import java.util.Date;

/**
 * Immutable class representing the details of a trade;
 * 
 * @author stleahy
 *
 */
public final class Trade {

	public enum Type {
		BUY,
		SELL
	}
	
	private final String stockSymbol;
	private final int quantity;
	private final int tradePrice;
	private final Date transactionTime;
	private final Type transactionType;
	
	public Trade(String stockSymbol, int quantity, int tradePrice, Date transactionTime, Type transactionType) {
		this.stockSymbol = stockSymbol;
		this.quantity = quantity;
		this.tradePrice = tradePrice;
		this.transactionTime = transactionTime;
		this.transactionType = transactionType;
	}
	
	public String getStockSymbol() {
		return stockSymbol;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getTradePrice() {
		return tradePrice;
	}
	
	public Date getTransactionTime() {
		return new Date(transactionTime.getTime());
	}

	public Type getTransactionType() {
		return transactionType;
	}
}