package com.jpm.simplestocks.util;

import java.math.BigDecimal;
import java.util.List;

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

	public static double geometricMean(List<Double> values) {
		if (values == null || values.size() == 0) {
			return -1;
		}
		double product = multiplyNumbers(values);
		double power = divideUsingBigDecimal(1, values.size(), 5);
		double result = Math.pow(product, power);
		BigDecimal bd = new BigDecimal(result); // use BigDecimal to round to 2
												// decimal points
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return bd.doubleValue();
	}

	public static double multiplyNumbers(List<Double> values) {
		if (values == null || values.size() == 0) {
			return -1;
		}
		
		if(values.size() == 1) {
			return values.get(0);
		}

		BigDecimal product = new BigDecimal(1);
		for (Double value : values) {
			BigDecimal next = new BigDecimal(value);
			product = product.multiply(next);
		}

		product = product.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return product.doubleValue();
	}
}
