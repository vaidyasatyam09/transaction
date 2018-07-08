package com.n26.transaction_app;

import comparator.TransactionDataComparator;
import model.StatisticsData;
import model.TransactionData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {


    private final PriorityQueue<TransactionData> transactions = new PriorityQueue<>(1000,new TransactionDataComparator());

    private StatisticsData statistics = new StatisticsData(transactions);

    public void saveTransaction(TransactionData transaction){
        transactions.add(transaction);
        updateStatistics();
    }

    @Scheduled(fixedRate = 1)
    private void removeTransactionOlderThan60Sec() {
        while (!transactions.isEmpty() && !transactions.peek().isWithinTimeLimit()) {
            transactions.poll();
        }
        updateStatistics();
    }

    public void updateStatistics(){
        statistics=new StatisticsData(transactions);
    }

    public StatisticsData getStatistics() {
        return statistics;
    }
}
