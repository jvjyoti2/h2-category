package com.internetBankingApp.h2category.controller;

import com.internetBankingApp.h2category.customExceptions.CategoryNotFoundException;
import com.internetBankingApp.h2category.entity.Transactions;
import com.internetBankingApp.h2category.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;
import java.util.Map;

@EnableConfigurationProperties
@RestController
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/transaction/{category}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Transactions>> categoryWiseTransactions(@Validated @RequestBody @PathVariable("category") String category) {

        List<Transactions> result = transactionService.firstTransactionByCategory(category);
        if (result == null || result.isEmpty())
            throw new CategoryNotFoundException(category + " category is not present.");
        return new ResponseEntity(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/outgoings", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Map<String, Double>> categoryWiseTotalOutgoing() {

        Map<String, Double> result = transactionService.outgoingTransactionByCategory();
        if (result == null || result.isEmpty())
            throw new CategoryNotFoundException("Invalid input");
        return new ResponseEntity(result, HttpStatus.OK);

    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/transaction/avg/{category}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Map<Month, Double>> categoryWiseMonthlyAvgSpend(@Validated @RequestBody @PathVariable("category") String category) {

        Map<Month, Double> result = transactionService.monthlyAverageSpendByCategory(category);
        if (result == null || result.isEmpty())
            throw new CategoryNotFoundException(category + " category is not present.");
        return new ResponseEntity(result, HttpStatus.OK);

    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/transaction/high/{category}/{year}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Transactions> categoryWiseHighestSpend(@Validated @RequestBody @PathVariable("category") String category, @PathVariable("year") String year) {

        Transactions result = transactionService.highestTransactionByCategory(category, year);
        if (result == null)
            throw new CategoryNotFoundException(category + " category is not present.");
        return new ResponseEntity(result, HttpStatus.OK);

    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/transaction/low/{category}/{year}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Transactions> categoryWiseLowestSpend(@Validated @RequestBody @PathVariable("category") String category, @PathVariable("year") String year) {

        Transactions result = transactionService.lowestTransactionByCategory(category, year);
        if (result == null)
            throw new CategoryNotFoundException(category + " category is not present.");
        return new ResponseEntity(result, HttpStatus.OK);

    }
}
