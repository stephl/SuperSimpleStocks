package com.jpm.simplestocks.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.jpm.simplestocks.util.StockUtils;

public class PreferredStock extends Stock {

	private double fixedDividendPercentage;
	
	public PreferredStock(String symbol, int lastDividend, int parValue, double fixedDividendPercentage) {
		super(symbol, lastDividend, parValue);
		this.setFixedDividend(fixedDividendPercentage);
	}

	public double getFixedDividendPercentage() {
		return fixedDividendPercentage;
	}

	public void setFixedDividend(double fixedDividendPercentage) {
		this.fixedDividendPercentage = fixedDividendPercentage;
	}

	@Override
	public double getDividendYield(int marketPrice) {
		double dividend = getPercentageAsDouble() * getParValue();
		return StockUtils.divideUsingBigDecimal(dividend, marketPrice);
	}

	private double getPercentageAsDouble() {
		if(fixedDividendPercentage <= 0) {
			return -1;
		} 
			
		BigDecimal bd = new BigDecimal(fixedDividendPercentage).divide(new BigDecimal(100), 2, RoundingMode.DOWN);
		return bd.doubleValue();
	}
}