package com.jpm.simplestocks.utils;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.jpm.simplestocks.util.StockUtils;

public class TestStockUtils {

	@Test
	public void testMultiplyNumbers() {
		List<Double> numbers = Arrays.asList(new Double[] { 1.0, 2.0, 3.0 });
		double result = StockUtils.multiplyNumbers(numbers);
		Assert.assertEquals(6, result, 0);
	}

	@Test
	public void testMultiplyNumbersWithFractions() {
		List<Double> numbers = Arrays.asList(new Double[] { 1.1, 1.5, 3.2 });
		double result = StockUtils.multiplyNumbers(numbers);
		Assert.assertEquals(5.28, result, 0);
	}

	@Test
	public void testGeomtricMeanSimple() {
		List<Double> numbers = Arrays.asList(new Double[] { 1.0, 2.0, 3.0 });
		double result = StockUtils.geometricMean(numbers);
		Assert.assertEquals(1.82, result, 0);
	}

	@Test
	public void testGeomtricMeanWithFractions() {
		List<Double> numbers = Arrays.asList(new Double[] { 10.5, 3.7, 12.1, 2.2, 4.3 });
		double result = StockUtils.geometricMean(numbers);
		Assert.assertEquals(5.37, result, 0);
	}

	@Test
	public void testGeomtricMeanNoValues() {
		List<Double> numbers = Arrays.asList(new Double[] { });
		double result = StockUtils.geometricMean(numbers);
		Assert.assertEquals(-1, result, 0);
	}
}
