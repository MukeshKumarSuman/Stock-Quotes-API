package com.nps.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "StockQuotes")
public class StockQuotesEntity {
	
	@Id
	private String stockQuote;
	private String companyName;
	
	@JsonFormat(pattern="dd-MM-yy")
	private LocalDate date;
	private double price;
	private String currency;
	public StockQuotesEntity() {
	}
	public StockQuotesEntity(String stockQuote, String companyName, LocalDate date, double price, String currency) {
		this.stockQuote = stockQuote;
		this.companyName = companyName;
		this.date = date;
		this.price = price;
		this.currency = currency;
	}
	public String getStockQuote() {
		return stockQuote;
	}
	public void setStockQuote(String stockQuote) {
		this.stockQuote = stockQuote;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "StockQuotesEntity [stockQuote=" + stockQuote + ", companyName=" + companyName + ", date=" + date
				+ ", price=" + price + ", currency=" + currency + "]";
	}
}
