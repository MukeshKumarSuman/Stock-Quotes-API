package com.nps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nps.entity.StockQuotesEntity;

public interface StockQuotesRepostory extends JpaRepository<StockQuotesEntity, String>{

}
