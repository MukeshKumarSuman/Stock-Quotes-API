package com.nps.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nps.entity.StockQuotesEntity;
import com.nps.modal.Stocks;
import com.nps.service.StockQuotesService;

@RestController
@RequestMapping("/stockQuote")
public class StockQuotesRestController {
	
	@Autowired
	private StockQuotesService service;
	
	@PostMapping(value = "/addQuote", produces = { "application/json"}, consumes = {"application/json"})
	public ResponseEntity<Stocks> addQuote(@RequestBody StockQuotesEntity stockQuotes) {
		Stocks stock = service.addQuote(stockQuotes);
		return new ResponseEntity<Stocks>(stock, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/quotes", produces = { "application/json"})
	public ResponseEntity<Stocks> getAllQuote() {
		Stocks quotes = service.getQuote();
		return new ResponseEntity<Stocks>(quotes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/queryQuote", produces = { "application/json"})
	public ResponseEntity<Stocks> getQueryQuotes(@RequestParam String symbols) {
		Stocks quotes = service.getQueryQuotes(symbols);
		return new ResponseEntity<Stocks>(quotes, HttpStatus.OK);
	}
}
