package com.nps.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nps.entity.StockQuotesEntity;
import com.nps.modal.Stocks;
import com.nps.service.StockQuotesService;

@WebMvcTest
public class StockQuotesRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StockQuotesService service;
	
	@Test
	public void testAddQuote() throws Exception {
		LocalDate date = LocalDate.of(2022, 04, 12);
		StockQuotesEntity quote = new StockQuotesEntity("IBM", "IBM Technologies", date, 250, "INR");
		Stocks stocks = new Stocks(Arrays.asList(quote));
		Mockito.when(service.addQuote(ArgumentMatchers.any())).thenReturn(stocks);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		
		String json = mapper.writeValueAsString(stocks);
		String quoteJson = mapper.writeValueAsString(quote);
		String postUri = "/stockQuote/addQuote";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(postUri).contentType(MediaType.APPLICATION_JSON_VALUE).content(quoteJson).accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.stocks.size()", is(stocks.getStocks().size())))
		.andExpect(jsonPath("$.stocks.[0].stockQuote", is("IBM")))
		.andReturn();
		assertNotNull(result);
        String expectedJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson, json);
	}
	
	@Test
	public void testGetQuotes() throws Exception {
		StockQuotesEntity ibmQuote = new StockQuotesEntity("IBM", "IBM Technologies", LocalDate.of(2022, 05, 8), 250.0789, "INR");
		StockQuotesEntity hclQuote = new StockQuotesEntity("HCL", "HCL Technologies", LocalDate.of(2022, 04, 11), 250.00, "INR");
		Stocks stocks = new Stocks(Arrays.asList(ibmQuote, hclQuote));
		
		Mockito.when(service.getQueryQuotes(ArgumentMatchers.any())).thenReturn(stocks);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		
		String json = mapper.writeValueAsString(stocks);
		String getUri = "/stockQuote/queryQuote";
		String param = "IBM,HCL";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(getUri).param("symbols", param).contentType(MediaType.APPLICATION_JSON_VALUE).content(json).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
		.andReturn();
		assertNotNull(result);
        String expectedJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson, json);
        
        Stocks readValue = mapper.readValue(expectedJson, new TypeReference<Stocks>() {});
        assertTrue(readValue.getStocks().size() <= 10);
        assertTrue(readValue.getStocks().size() == 2);
	}
	
	@Test
	public void testGetQuotesWhenSizeGreaterThanT10() throws Exception {
		StockQuotesEntity ibmQuote = new StockQuotesEntity("IBM", "IBM Technologies", LocalDate.of(2022, 05, 8), 250.0789, "INR");
		StockQuotesEntity hclQuote = new StockQuotesEntity("HCL", "HCL Technologies", LocalDate.of(2022, 04, 11), 250.00, "INR");
		Stocks stocks = new Stocks(Arrays.asList(ibmQuote, hclQuote));
		
		Mockito.when(service.getQueryQuotes(ArgumentMatchers.any())).thenReturn(stocks);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		
		String json = mapper.writeValueAsString(stocks);
		String getUri = "/stockQuote/queryQuote";
		String param = "IBM,HCL,XYZ1,XYZ2,XYZ3,XYZ4,XYZ5,XYZ6,XYZ7,XYZ8,XYZ9";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(getUri).param("symbols", param).contentType(MediaType.APPLICATION_JSON_VALUE).content(json).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
		.andReturn();
		assertNotNull(result);
        String expectedJson = result.getResponse().getContentAsString();
        assertEquals(expectedJson, json);
        
        Stocks readValue = mapper.readValue(expectedJson, new TypeReference<Stocks>() {});
        assertTrue(readValue.getStocks().size() <= 10);
        assertTrue(readValue.getStocks().size() == 2);
	}
}
