package com.jpm.simplestocks.model;

import com.jpm.simplestocks.util.StockUtils;

public abstract class Stock {

	private final String symbol;
	private int lastDividend;
	private int parValue;
	
	public Stock(String symbol, int lastDividend, int parValue) {
		this.symbol = symbol;
		this.lastDividend = lastDividend;
		this.parValue = parValue;
	}
	
	public abstract double getDividendYield(int marketPrice);

	public String getSymbol() {
		return symbol;
	}
	
	public int getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(int lastDividend) {
		this.lastDividend = lastDividend;
	}

	public int getParValue() {
		return parValue;
	}

	public void setParValue(int parValue) {
		this.parValue = parValue;
	}
	
	public double getPeRatio(int marketPrice) {
		return StockUtils.divideUsingBigDecimal(marketPrice, lastDividend);				
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Stock){
			Stock compareTo = (Stock)o;
			if(compareTo.getSymbol().equals(symbol)){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return symbol.hashCode();
	}
}