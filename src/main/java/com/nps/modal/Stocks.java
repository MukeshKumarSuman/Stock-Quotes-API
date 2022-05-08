package com.nps.modal;

import java.util.List;

import com.nps.entity.StockQuotesEntity;

public class Stocks {
	private List<StockQuotesEntity> stocks;

	public Stocks() {}

	public Stocks(List<StockQuotesEntity> stocks) {
		this.stocks = stocks;
	}

	public List<StockQuotesEntity> getStocks() {
		return stocks;
	}

	public void setStocks(List<StockQuotesEntity> stocks) {
		this.stocks = stocks;
	}

	@Override
	public String toString() {
		return "Stocks [stocks=" + stocks + "]";
	}
	
}
