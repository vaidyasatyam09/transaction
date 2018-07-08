package com.n26.transaction_app;

import model.StatisticsData;
import model.TransactionData;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class TransactionService {

    private Map<Long, StatisticsData> transactions = new HashMap<>();

    public void saveTransaction(TransactionData transaction){
        System.out.println("Time now: "+Instant.now().toEpochMilli());
        boolean validTransation=validateTransaction(transaction);
        if(validTransation) {
            StatisticsData stats = new StatisticsData(transaction);
            if (transactions.containsKey(transaction.getTimeStamp())) {
                transactions.get(transaction.getTimeStamp()).updateStatistics(stats);
            } else
                transactions.put(transaction.getTimeStamp(), stats);
        }
    }

    private boolean validateTransaction(TransactionData transaction) {
        if(null!=transaction & checkValidTime(transaction.getTimeStamp()) )
            return true;
        else
            return false;
    }

    public boolean checkValidTime(long time){
        long timeBefore60Sec = Instant.now().minus(60, ChronoUnit.SECONDS).toEpochMilli();
        return time > timeBefore60Sec;
    }

    public StatisticsData getStatistics(){
        updateMap();
        if(transactions.isEmpty()) return new StatisticsData();

        double sum=transactions.values().stream().mapToDouble(StatisticsData::getSum).sum();
        long count=transactions.values().stream().mapToLong(StatisticsData::getCount).sum();
        double avg=sum/count;
        double max=transactions.values().stream().mapToDouble(StatisticsData::getMax).max().getAsDouble();
        double min=transactions.values().stream().mapToDouble(StatisticsData::getMin).min().getAsDouble();

        StatisticsData aggregatedStatistics= new StatisticsData(sum,avg,max,min,count);
        return aggregatedStatistics;
    }

    private void updateMap() {
        for(Iterator<Map.Entry<Long, StatisticsData>> it = transactions.entrySet().iterator(); it.hasNext();){
            Map.Entry<Long, StatisticsData> entry = it.next();
            if(!checkValidTime(entry.getKey())) {
                it.remove();
            }
        }
    }
}
