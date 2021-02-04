package com.internetBankingApp.h2category.dao;

import com.internetBankingApp.h2category.entity.Transactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends CrudRepository<Transactions, Integer> {

}
