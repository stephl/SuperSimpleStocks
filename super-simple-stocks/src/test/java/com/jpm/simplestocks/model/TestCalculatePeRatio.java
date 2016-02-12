package com.jpm.simplestocks.model;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestCalculatePeRatio {

	private int marketPrice;
	private int lastDividend;
	private double expected;

	public TestCalculatePeRatio(int marketPrice, int lastDividend, double expected) {

		this.marketPrice = marketPrice;
		this.lastDividend = lastDividend;
		this.expected = expected;
	}

	@Parameters(name = "Market Price: {0} Last Dividend: {1} Result: {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 10, 5, 2 }, // Simple case
				{ 8, 3, 2.67 }, // Fractional Result
				{ 0, 5, -1 }, // Zero market price
				{ 10, 0, -1 }, // Zero Dividend		
				{ -1, 5, -1 }, // Zero market price
				{ 10, -1, -1 } // Zero Dividend		
		});
	}

	@Test
	public void testCalculatePreferredPeRatio() {
		Stock stock = new PreferredStock("TEST", lastDividend, 0, 0);
		double result = stock.getPeRatio(marketPrice);
		Assert.assertEquals(result, this.expected, 0);
	}
	
	@Test
	public void testCalculateCommonPeRatio() {
		Stock stock = new CommonStock("TEST", lastDividend, 0);
		double result = stock.getPeRatio(marketPrice);
		Assert.assertEquals(result, this.expected, 0);
	}
}
