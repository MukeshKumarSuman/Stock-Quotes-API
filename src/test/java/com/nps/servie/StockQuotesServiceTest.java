package com.nps.servie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nps.entity.StockQuotesEntity;
import com.nps.modal.Stocks;
import com.nps.repository.StockQuotesRepostory;
import com.nps.service.StockQuotesService;

@ExtendWith(MockitoExtension.class)
public class StockQuotesServiceTest {
	
	@InjectMocks
	private StockQuotesService service;
	
	@Mock
	private StockQuotesRepostory repo;
	
	@Test
	public void testAddQuote() {
		LocalDate date = LocalDate.of(2022, 04, 12);
		StockQuotesEntity quote = new StockQuotesEntity("IBM", "IBM Technologies", date, 250, "INR");
		Mockito.when(repo.save(quote)).thenReturn(quote);
		Stocks addedQuote = service.addQuote(quote);
		StockQuotesEntity stockQuotesEntity = addedQuote.getStocks().get(0);
		assertThat(stockQuotesEntity.getStockQuote()).isEqualTo(quote.getStockQuote());
	}
	
	@Test
	public void testGetQueryQuotes() {
		StockQuotesEntity ibmQuote = new StockQuotesEntity("IBM", "IBM Technologies", LocalDate.of(2022, 05, 8), 250.0789, "INR");
		StockQuotesEntity hclQuote = new StockQuotesEntity("HCL", "HCL Technologies", LocalDate.of(2022, 04, 11), 250.00, "INR");
		String queryString = "IBM,HCL";
		List<String> queryList = Arrays.asList("IBM", "HCL");
		List<StockQuotesEntity> stocksList = Arrays.asList(ibmQuote, hclQuote);
		Mockito.when(repo.findAllById(queryList)).thenReturn(stocksList);
		
		Stocks stock = service.getQueryQuotes(queryString);
		assertTrue(stock.getStocks().size() < 11);
	}
	
	@Test
	public void testGetQuotesWhenSizeGreaterThanT10() throws Exception {
		StockQuotesEntity ibmQuote = new StockQuotesEntity("IBM", "IBM Technologies", LocalDate.of(2022, 05, 8), 250.0789, "INR");
		StockQuotesEntity hclQuote = new StockQuotesEntity("HCL", "HCL Technologies", LocalDate.of(2022, 04, 11), 250.00, "INR");
		String queryString = "IBM,HCL,XYZ1,XYZ2,XYZ3,XYZ4,XYZ5,XYZ6,XYZ7,XYZ8,XYZ9";
		List<String> queryList = Arrays.asList("IBM", "HCL");
		List<StockQuotesEntity> stocksList = Arrays.asList(ibmQuote, hclQuote);
		assertThrows(RuntimeException.class, () ->  service.getQueryQuotes(queryString));
		
	}
}
