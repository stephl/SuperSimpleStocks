package com.jpm.simplestocks.model;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.jpm.simplestocks.model.Trade.Type;

public class TestTrade {

	@Test
	public void testCreateValidTrade() {
		Trade trade = new Trade("TEA", 10, 10, new Date(), Type.BUY);
		Assert.assertNotNull(trade);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateTradeNoSymbol() {
		new Trade(null, 10, 10, new Date(), Type.BUY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateTradeNegativeQuantity() {
		new Trade("TEA", -1, 10, new Date(), Type.BUY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateTradeNegativePrice() {
		new Trade("TEA", 5, -10, new Date(), Type.BUY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateTradeNoDate() {
		new Trade("TEA", 5, -10, null, Type.BUY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateTradeNoType() {
		new Trade("TEA", 5, 10, new Date(), null);
	}
}
