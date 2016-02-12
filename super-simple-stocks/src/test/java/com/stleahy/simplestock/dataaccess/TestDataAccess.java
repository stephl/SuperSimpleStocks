package com.stleahy.simplestock.dataaccess;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.jpm.simpestocks.dataaccess.DataAccessProvider;
import com.jpm.simpestocks.dataaccess.InMemoryDataAccessProvider;
import com.jpm.simplestocks.model.PreferredStock;
import com.jpm.simplestocks.model.Stock;
import com.jpm.simplestocks.model.Trade;

public class TestDataAccess {

	private DataAccessProvider dap = new InMemoryDataAccessProvider();

	@Test
	public void testAddStock() {
		Stock stock = new PreferredStock("TEST", 10, 10, 2);
		boolean added = dap.addStock(stock);
		Assert.assertTrue(added);
	}

	@Test
	public void testAddDuplicateStock() {
		Stock stock = new PreferredStock("TEST", 10, 10, 2);
		boolean added = dap.addStock(stock);
		added = dap.addStock(stock);
		Assert.assertFalse(added);
	}

	@Test
	public void testAddNullStock() {
		boolean added = dap.addStock(null);
		Assert.assertFalse(added);
	}

	@Test
	public void testUpdateStock() {
		Stock stock = new PreferredStock("TEST", 10, 10, 2);
		boolean added = dap.addStock(stock);
		Assert.assertTrue(added);

		stock.setParValue(20);
		boolean updated = dap.updateStock(stock);
		Assert.assertTrue(updated);
	}

	@Test
	public void testUpdateNullStock() {
		Stock stock = new PreferredStock("TEST", 10, 10, 2);
		boolean added = dap.addStock(stock);
		Assert.assertTrue(added);

		stock.setParValue(20);
		boolean updated = dap.updateStock(null);
		Assert.assertFalse(updated);
	}

	@Test
	public void testRecordTrade() {
		Trade trade = new Trade("TEST", 10, 10, new Date(), Trade.Type.BUY);
		boolean recorded = dap.recordTrade(trade);
		Assert.assertTrue(recorded);
	}

	@Test
	public void testRecordNullTrade() {
		boolean recorded = dap.recordTrade(null);
		Assert.assertFalse(recorded);
	}

	@Test
	public void testGetAllTradesNoTradesMade() {
		Map<String, List<Trade>> allTrades = dap.getAllTrades();
		Assert.assertEquals(0, allTrades.size());
	}

	@Test
	public void testGetAllTradesForMulipleStocks() {
		Trade trade1 = new Trade("TEST", 10, 10, new Date(), Trade.Type.BUY);
		Trade trade2 = new Trade("TEST", 10, 10, new Date(), Trade.Type.SELL);
		Trade trade3 = new Trade("TEST2", 10, 10, new Date(), Trade.Type.BUY);
		Trade trade4 = new Trade("TEST2", 10, 10, new Date(), Trade.Type.SELL);
		Trade trade5 = new Trade("TEST2", 10, 10, new Date(), Trade.Type.SELL);
		dap.recordTrade(trade1);
		dap.recordTrade(trade2);
		dap.recordTrade(trade3);
		dap.recordTrade(trade4);
		dap.recordTrade(trade5);

		Map<String, List<Trade>> allTrades = dap.getAllTrades();
		Assert.assertEquals(2, allTrades.keySet().size());

		List<Trade> testTrades = allTrades.get("TEST");
		Assert.assertEquals(2, testTrades.size());

		List<Trade> test2Trades = allTrades.get("TEST2");
		Assert.assertEquals(3, test2Trades.size());
	}
}
