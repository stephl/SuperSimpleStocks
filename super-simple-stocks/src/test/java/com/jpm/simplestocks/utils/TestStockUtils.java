package com.jpm.simplestocks.utils;

import org.junit.Assert;
import org.junit.Test;

import com.jpm.simplestocks.util.StockUtils;

public class TestStockUtils {

	@Test
	public void testMultiplyIntegers() {
		double result = StockUtils.multiplyNumbers(1, 2, 3);
		Assert.assertEquals(6, result, 0);
	}

	@Test
	public void testMultiplyDoubles() {
		double result = StockUtils.multiplyNumbers(1.1, 1.5, 3.2);
		Assert.assertEquals(5.28, result, 0);
	}

	@Test
	public void testGeomtricMeanSimple() {
		double result = StockUtils.geometricMean(1, 2, 3);
		Assert.assertEquals(1.82, result, 0);
	}

	@Test
	public void testGeomtricMeanWithFractions() {
		double result = StockUtils.geometricMean(10.5, 3.7, 12.1, 2.2, 4.3);
		Assert.assertEquals(5.37, result, 0);
	}

	@Test
	public void testGeomtricMeanNoValues() {
		double result = StockUtils.geometricMean();
		Assert.assertEquals(-1, result, 0);
	}
}
