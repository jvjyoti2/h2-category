package com.internetBankingApp.h2category.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Transactions {

    private static final Logger logger = LoggerFactory.getLogger(Transactions.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String vendor, type, category;
    @JsonFormat(pattern = "dd-MMM-YYYY")
    private Date transactionDate;
    private float amount;
    // Default constructor
    public Transactions() {
    }

    public Transactions(long id, String vendor, String type, String category, Date transactionDate, float amount) {
        this.id = id;
        this.vendor = vendor;
        this.type = type;
        this.category = category;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", vendor='" + vendor + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", amount=" + amount +
                '}';
    }
}
