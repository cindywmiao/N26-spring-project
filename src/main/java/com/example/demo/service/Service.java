package com.example.demo.service;

import com.example.demo.model.StatisticData;
import com.example.demo.model.TransactionData;

public interface Service {
    StatisticData getRealTimeStatistic();

    boolean addTransactionData(TransactionData transactionData);
}
