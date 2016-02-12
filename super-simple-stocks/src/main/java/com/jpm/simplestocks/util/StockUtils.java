package com.jpm.simplestocks.util;

import java.math.BigDecimal;

public class StockUtils {

	public static double divideUsingBigDecimal(double number, double divisor) {
		return divideUsingBigDecimal(number, divisor, 2);
	}

	public static double divideUsingBigDecimal(double number, double divisor, int precision) {
		if (number <= 0 || divisor <= 0) {
			return -1;
		}

		BigDecimal numberAsBD = new BigDecimal(number);
		BigDecimal divisorAsBD = new BigDecimal(divisor);
		BigDecimal resultAsBD = numberAsBD.divide(divisorAsBD, precision, BigDecimal.ROUND_HALF_DOWN);
		return resultAsBD.doubleValue();
	}

	public static double geometricMean(double... value) {
		if (value == null || value.length == 0) {
			return -1;
		}
		double product = multiplyNumbers(value);
		double power = divideUsingBigDecimal(1, value.length, 5);
		double result = Math.pow(product, power);
		BigDecimal bd = new BigDecimal(result); // use BigDecimal to round to 2
												// decimal points
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return bd.doubleValue();
	}

	public static double multiplyNumbers(double... value) {
		if (value == null || value.length == 0) {
			return -1;
		}

		BigDecimal product = new BigDecimal(value[0]);
		for (int i = 1; i < value.length; i++) {
			BigDecimal next = new BigDecimal(value[i]);
			product = product.multiply(next);
		}

		product = product.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return product.doubleValue();
	}
}
