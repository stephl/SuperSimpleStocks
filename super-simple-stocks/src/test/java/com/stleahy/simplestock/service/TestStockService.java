package com.stleahy.simplestock.service;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.jpm.simplestocks.model.CommonStock;
import com.jpm.simplestocks.model.Stock;
import com.jpm.simplestocks.model.Trade;

public class TestStockService {

	@Test
	public void testVolumeWeightedStockPriceSingleTrade() {
		SimpleStockService service = new SimpleStockService();
		Stock ale = new CommonStock("ALE", 23, 60);
		service.addStock(ale);

		Trade trade = new Trade("ALE", 50, 100, new Date(), Trade.Type.BUY);
		service.recordTrade(trade);
		double result = service.calculateVolumeWeightedStockPrice("ALE");
		Assert.assertEquals(100, result, 0);
	}

	@Test
	public void testVolumeWeightedStockPriceMultipleTrades() {
		SimpleStockService service = new SimpleStockService();
		Stock ale = new CommonStock("ALE", 23, 60);
		service.addStock(ale);

		Trade trade = new Trade("ALE", 50, 100, new Date(), Trade.Type.BUY);
		Trade trade2 = new Trade("ALE", 5, 95, new Date(), Trade.Type.BUY);
		Trade trade3 = new Trade("ALE", 7, 50, new Date(), Trade.Type.BUY);
		Trade trade4 = new Trade("ALE", 1, 101, new Date(), Trade.Type.BUY);
		service.recordTrade(trade);
		service.recordTrade(trade2);
		service.recordTrade(trade3);
		service.recordTrade(trade4);

		double result = service.calculateVolumeWeightedStockPrice("ALE");
		Assert.assertEquals(94.06, result, 0);
	}

	@Test
	public void testVolumeWeightedStockPriceNoTrades() {
		SimpleStockService service = new SimpleStockService();
		Stock ale = new CommonStock("ALE", 23, 60);
		service.addStock(ale);

		double result = service.calculateVolumeWeightedStockPrice("ALE");
		Assert.assertEquals(0, result, 0);
	}

	@Test
	public void testVolumeWeightedStockPriceUnknownStock() {
		SimpleStockService service = new SimpleStockService();
		double result = service.calculateVolumeWeightedStockPrice("UKNOWN");
		Assert.assertEquals(0, result, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testVolumeWeightedStockPriceNullStock() {
		SimpleStockService service = new SimpleStockService();
		double result = service.calculateVolumeWeightedStockPrice(null);
		Assert.assertEquals(0, result, 0);
	}

	@Test
	public void testVolumeWeightedStockPriceNoRecentTrades() {
		SimpleStockService service = new SimpleStockService();
		Stock ale = new CommonStock("ALE", 23, 60);
		service.addStock(ale);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, -20);
		Date twentyMinsPast = calendar.getTime();
		Trade trade = new Trade("ALE", 50, 100, twentyMinsPast, Trade.Type.BUY);
		Trade trade2 = new Trade("ALE", 5, 95, twentyMinsPast, Trade.Type.BUY);
		Trade trade3 = new Trade("ALE", 7, 50, twentyMinsPast, Trade.Type.BUY);
		Trade trade4 = new Trade("ALE", 1, 101, twentyMinsPast, Trade.Type.BUY);
		service.recordTrade(trade);
		service.recordTrade(trade2);
		service.recordTrade(trade3);
		service.recordTrade(trade4);

		double result = service.calculateVolumeWeightedStockPrice("ALE");
		Assert.assertEquals(0, result, 0);
	}
	
	@Test
	public void testVolumeWeightedStockPriceMixedTradeTimes() {
		SimpleStockService service = new SimpleStockService();
		Stock ale = new CommonStock("ALE", 23, 60);
		service.addStock(ale);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, -20);
		Date twentyMinsPast = calendar.getTime();
		Trade trade = new Trade("ALE", 50, 100, twentyMinsPast, Trade.Type.BUY);
		Trade trade2 = new Trade("ALE", 5, 95, new Date(), Trade.Type.BUY);
		Trade trade3 = new Trade("ALE", 7, 50, twentyMinsPast, Trade.Type.BUY);
		Trade trade4 = new Trade("ALE", 1, 101, new Date(), Trade.Type.BUY);
		service.recordTrade(trade);
		service.recordTrade(trade2);
		service.recordTrade(trade3);
		service.recordTrade(trade4);

		double result = service.calculateVolumeWeightedStockPrice("ALE");
		Assert.assertEquals(96, result, 0);
	}

	@Test
	public void testAllShareIndexNoTrades() {
		int result = -1;
		Assert.assertEquals(result, 0);
	}

	@Test
	public void testAllShareIndexSingleStock() {
		int result = -1;
		Assert.assertEquals(result, 0);
	}

	@Test
	public void testAllShareIndexMultipleStocks() {
		int result = -1;
		Assert.assertEquals(result, 0);
	}

}