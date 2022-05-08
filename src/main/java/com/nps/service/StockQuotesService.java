package com.nps.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nps.entity.StockQuotesEntity;
import com.nps.exception.StockCountLimitExceedException;
import com.nps.modal.Stocks;
import com.nps.repository.StockQuotesRepostory;

@Service
public class StockQuotesService {
	
	@Autowired
	private StockQuotesRepostory repo;
	
	public Stocks addQuote(StockQuotesEntity stockQuotes) {
		stockQuotes.setPrice(new BigDecimal(stockQuotes.getPrice()).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		StockQuotesEntity quote = repo.save(stockQuotes);
		return new Stocks(Arrays.asList(quote));
	}
	
	public Stocks getQuote() {
		List<StockQuotesEntity> stocksList = repo.findAll();
		return new Stocks(stocksList);
	}
	
	public Stocks getQueryQuotes(String queryQuote) {
		String[] quoteFor = queryQuote.split(",");
		if (quoteFor.length > 10) {
			throw new StockCountLimitExceedException("Plesae reduce size of query quote symbol");
		}
		List<String> queryList = Arrays.asList(quoteFor);
		List<StockQuotesEntity> stocksList = repo.findAllById(queryList);
		return new Stocks(stocksList);
	}
}
