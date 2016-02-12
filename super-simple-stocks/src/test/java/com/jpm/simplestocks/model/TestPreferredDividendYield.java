package com.jpm.simplestocks.model;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestPreferredDividendYield {

	private int marketPrice;
	private int lastDividend;
	private int fixedDividend;
	private int parValue;
	private double expected;

	public TestPreferredDividendYield(int fixedDividend,
			int parValue, int marketPrice, double expected) {
		
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
		this.marketPrice = marketPrice;
		this.expected = expected;
	}

	@Parameters(name = "Fixed Dividend: {0} Par Value: {1} Market Price: {2} Result: {3}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 10, 100, 5, 2 }, // Simple case
			{ 10, 100, 3, 3.33 }, // Fractional Result, rounded
			{ 0, 100, 10, -1 }, // Zero Dividend
			{ 2, 0, 10, -1 }, // Zero Par
			{ 2, 100, 0, -1 }, // Zero market price
			{ -1, 100, 10, -1 }, // Negative Dividend
			{ 2, -1, 10, -1 }, // Negative Par
			{ 2, 100, -1, -1 } // Negative Market Price			
		});
	}

	@Test
	public void testCalculatePreferredDividendYield() {
		Stock stock = new PreferredStock("TEST", lastDividend, parValue, fixedDividend);
		double result = stock.getDividendYield(marketPrice);
		Assert.assertEquals(this.expected, result, 0);
	}
}
