package com.jpm.simplestocks.model;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestCommonDividendYield {

	private int marketPrice;
	private int lastDividend;
	private double expected;

	public TestCommonDividendYield(int lastDividend, int marketPrice, double expected) {

		this.marketPrice = marketPrice;
		this.lastDividend = lastDividend;
		this.expected = expected;
	}

	@Parameters(name = "Last Dividend: {0} Market Price: {1} Result: {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 10, 5, 2 }, // Simple case
				{ 20, 3, 6.67 }, // Fractional Result
				{ 0, 5, -1 }, // Zero Dividend
				{ 10, 0, -1 }, // Zero market price
				{ -1, 5, -1 }, // Negative Dividend
				{ 10, -1, -1 } // Negative Market Price
		});
	}

	@Test
	public void testCalculateCommonDividendYield() {
		Stock stock = new CommonStock("TEST", lastDividend, 0);
		double result = stock.getDividendYield(marketPrice);
		Assert.assertEquals(result, this.expected, 0);
	}
}
