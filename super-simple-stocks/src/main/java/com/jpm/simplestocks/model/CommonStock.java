package com.jpm.simplestocks.model;

import com.jpm.simplestocks.util.StockUtils;

public class CommonStock extends Stock {
	
	public CommonStock(String symbol, int lastDividend, int parValue) {
		super(symbol, lastDividend, parValue);
	}

	@Override
	public double getDividendYield(int marketPrice) {
		return StockUtils.divideUsingBigDecimal(getLastDividend(), marketPrice); 
	}
}