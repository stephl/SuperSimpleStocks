package com.jpm.simplestocks.service;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.jpm.simplestocks.model.CommonStock;
import com.jpm.simplestocks.model.Stock;
import com.jpm.simplestocks.model.Trade;
import com.jpm.simplestocks.service.SimpleStockService;

public class TestStockService {

	@Test
	public void testVolumeWeightedStockPriceSingleTrade() {
		SimpleStockService service = new SimpleStockService();
		Stock ale = new CommonStock("ALE", 23, 60);
		service.addStock(ale);

		Trade trade = new Trade("ALE", 50, 100, new Date(), Trade.Type.BUY);
		service.recordTrade(trade);
		double result = service.calculateStockPrice("ALE");
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

		double result = service.calculateStockPrice("ALE");
		Assert.assertEquals(94.06, result, 0);
	}

	@Test
	public void testVolumeWeightedStockPriceNoTrades() {
		SimpleStockService service = new SimpleStockService();
		Stock ale = new CommonStock("ALE", 23, 60);
		service.addStock(ale);

		double result = service.calculateStockPrice("ALE");
		Assert.assertEquals(0, result, 0);
	}

	@Test
	public void testVolumeWeightedStockPriceUnknownStock() {
		SimpleStockService service = new SimpleStockService();
		double result = service.calculateStockPrice("UKNOWN");
		Assert.assertEquals(0, result, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testVolumeWeightedStockPriceNullStock() {
		SimpleStockService service = new SimpleStockService();
		double result = service.calculateStockPrice(null);
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

		double result = service.calculateStockPrice("ALE");
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

		double result = service.calculateStockPrice("ALE");
		Assert.assertEquals(96, result, 0);
	}

	@Test
	public void testAllShareIndexNoTrades() {
		SimpleStockService service = new SimpleStockService();
		double result = service.calculateAllShareIndex();
		Assert.assertEquals(0, result, 0);
	}
	
	@Test
	public void testAllShareIndexSingleStock() {
		SimpleStockService service = new SimpleStockService();
		Trade trade = new Trade("ALE", 1, 100, new Date(), Trade.Type.BUY);
		Trade trade1 = new Trade("ALE", 2, 100, new Date(), Trade.Type.BUY);
		Trade trade2 = new Trade("ALE", 3, 100, new Date(), Trade.Type.BUY);
		Trade trade3 = new Trade("ALE", 4, 100, new Date(), Trade.Type.BUY);
		Trade trade4 = new Trade("ALE", 5, 100, new Date(), Trade.Type.BUY);
		Trade trade5 = new Trade("ALE", 6, 100, new Date(), Trade.Type.BUY);
		service.recordTrade(trade);
		service.recordTrade(trade1);
		service.recordTrade(trade2);
		service.recordTrade(trade3);
		service.recordTrade(trade4);
		service.recordTrade(trade5);
		
		double result = service.calculateAllShareIndex();
		Assert.assertEquals(100, result, 0);
	}

	@Test
	public void testAllShareIndexMultipleStocks() {
		SimpleStockService service = new SimpleStockService();
		Trade trade = new Trade("ALE", 1, 55, new Date(), Trade.Type.BUY);
		Trade trade1 = new Trade("ALE", 2, 58, new Date(), Trade.Type.BUY);
		Trade trade2 = new Trade("GIN", 3, 21, new Date(), Trade.Type.BUY);
		Trade trade3 = new Trade("GIN", 4, 13, new Date(), Trade.Type.BUY);
		Trade trade4 = new Trade("JOE", 5, 3, new Date(), Trade.Type.BUY);
		Trade trade5 = new Trade("JOE", 6, 19, new Date(), Trade.Type.BUY);
		Trade trade6 = new Trade("COF", 7, 10, new Date(), Trade.Type.BUY);
		Trade trade7 = new Trade("COF", 8, 100, new Date(), Trade.Type.BUY);
		service.recordTrade(trade);
		service.recordTrade(trade1);
		service.recordTrade(trade2);
		service.recordTrade(trade3);
		service.recordTrade(trade4);
		service.recordTrade(trade5);
		service.recordTrade(trade6);
		service.recordTrade(trade7);
		
		Assert.assertEquals(57, service.calculateStockPrice("ALE"), 0);
		Assert.assertEquals(16.43, service.calculateStockPrice("GIN"), 0);
		Assert.assertEquals(11.73, service.calculateStockPrice("JOE"), 0);
		Assert.assertEquals(58, service.calculateStockPrice("COF"), 0);
		
		double result = service.calculateAllShareIndex();
		Assert.assertEquals(28.25, result, 0);
	}

}