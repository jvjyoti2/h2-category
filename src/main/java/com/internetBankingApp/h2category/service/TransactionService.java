package com.internetBankingApp.h2category.service;

import com.internetBankingApp.h2category.dao.TransactionRepo;
import com.internetBankingApp.h2category.entity.Transactions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepo transactionRepo;

    // Method to fetch all the transactions from the db
    public List<Transactions> getAllTransactions(){

        logger.info("Service: Fetching transactions from Database");
        List<Transactions> listOfTransactions = new ArrayList<Transactions>();
        transactionRepo.findAll().forEach(listOfTransactions::add);
        return listOfTransactions;
    }

    // Method to get all the transactions for a given category - Latest First
    public List<Transactions> firstTransactionByCategory(String category){

        logger.info("Service: firstTransactionByCategory started");

        List<Transactions> listOfTran;
        try{
            listOfTran = getAllTransactions();
        }
        catch(RuntimeException e){
            throw new RuntimeException("Exception while fetching data from H2");
        }

        logger.info("Service: Fetching transactions with category {}.", category);

        try{
            List<Transactions> lis = listOfTran
                    .stream()
                    .filter(n -> n.getCategory().equalsIgnoreCase(category))
                    .sorted(Comparator.comparing(Transactions::getTransactionDate).reversed())
                    .collect(Collectors.toList());

            return lis;
        }
        catch (RuntimeException e){
            throw new RuntimeException("Exception while sorting category");
        }
    }

    // Method to get total outgoing per category
    public Map<String, Double> outgoingTransactionByCategory(){

        logger.info("Service: outgoingTransactionByCategory started");

        List<Transactions> listOfTran;
        try{
            listOfTran = getAllTransactions();
        }
        catch(RuntimeException e){
            throw new RuntimeException("Exception while fetching data from H2");
        }

        logger.info("Service: Fetching transactions with total outgoing per category");

        try{
            Map<String, Double> lis = listOfTran
                    .stream()
                    .collect(Collectors.groupingBy(Transactions::getCategory,Collectors.summingDouble(Transactions::getAmount)));
            return lis;
        }
        catch (RuntimeException e){
            throw new RuntimeException("Exception while sorting category");
        }
    }

    // Method to get monthly average spend in a given category
    public Map<Month, Double> monthlyAverageSpendByCategory(String category){

        logger.info("Service: monthlyAverageSpendByCategory started");

        List<Transactions> listOfTran;
        try{
            listOfTran = getAllTransactions();
        }
        catch(RuntimeException e){
            throw new RuntimeException("Exception while fetching data from H2");
        }

        logger.info("Service: Fetching monthly average spend in category {}.", category);

        try{
            Map<Month, Double> counts = listOfTran.stream()
                    .filter(n -> n.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.groupingBy((Transactions e) -> {
                        return e.getTransactionDate().toLocalDate().getMonth();
                    },
                            Collectors.averagingDouble(Transactions::getAmount)
                    ));
            return counts;
        }
        catch (RuntimeException e){
            throw new RuntimeException("Exception while sorting category");
        }
    }

    public int yearlist(Transactions transactions){
        return  transactions.getTransactionDate().toLocalDate().getYear();
    }

    // Method to get highest spend in a given category, for a given year
    public Transactions highestTransactionByCategory(String category, String year){

        logger.info("Service: highestTransactionByCategory started");

        List<Transactions> listOfTran;
        try{
            listOfTran = getAllTransactions();
        }
        catch(RuntimeException e){
            throw new RuntimeException("Exception while fetching data from H2");
        }

        logger.info("Service: Fetching transaction with highest spend in category {} in the year {}.", category, year);

        try{
            Transactions high = listOfTran
                    .stream()
                    .filter(n -> n.getCategory().equalsIgnoreCase(category)
                            && (n.getTransactionDate().toString().substring(0,4).equalsIgnoreCase(year)))
                    .max(Comparator.comparing(Transactions::getAmount))
                    .orElseThrow(NoSuchElementException::new);
            return high;
        }
        catch (RuntimeException e){
            throw new RuntimeException("Exception while sorting category");
        }
    }

    // Method to get lowest spend in a given category, for a given year
    public Transactions lowestTransactionByCategory(String category, String year){

        logger.info("Service: lowestTransactionByCategory started");

        List<Transactions> listOfTran;
        try{
            listOfTran = getAllTransactions();
        }
        catch(RuntimeException e){
            throw new RuntimeException("Exception while fetching data from H2");
        }

        logger.info("Service: Fetching transaction with highest spend in category {} in the year {}.", category, year);

        try{
            Transactions high = listOfTran
                    .stream()

                    .filter(n -> n.getCategory().equalsIgnoreCase(category)
                            && (n.getTransactionDate().toString().substring(0,4).equalsIgnoreCase(year)))
                    .min(Comparator.comparing(Transactions::getAmount))
                    .orElseThrow(NoSuchElementException::new);
            return high;
        }
        catch (RuntimeException e){
            throw new RuntimeException("Exception while sorting category");
        }
    }

    public static Double roundoff (Double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
