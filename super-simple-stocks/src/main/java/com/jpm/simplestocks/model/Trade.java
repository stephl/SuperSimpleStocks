package com.jpm.simplestocks.model;

import java.util.Date;

/**
 * Immutable class representing the details of a trade;
 *
 */
public final class Trade {

	public enum Type {
		BUY, SELL
	}

	private final String stockSymbol;
	private final int quantity;
	private final int tradePrice;
	private final Date transactionTime;
	private final Type transactionType;

	public Trade(String stockSymbol, int quantity, int tradePrice, Date transactionTime, Type transactionType) {

		if(stockSymbol == null || stockSymbol.length() == 0) {
			throw new IllegalArgumentException("Trades must have a stock specified.");
		}
		
		if(quantity <= 0) {
			throw new IllegalArgumentException("Trades must have quantity of at least 1.");
		}
		
		if(tradePrice <= 0) {
			throw new IllegalArgumentException("Trades must have a trade price.");
		}		
		
		if(transactionTime == null) {
			throw new IllegalArgumentException("Trades must have a transaction time");
		}
		
		if(transactionType == null) {
			throw new IllegalArgumentException("Trades must have a transaction type");
		}
		
		
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