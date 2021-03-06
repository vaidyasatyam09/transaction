package com.n26.transaction_app.service;


import com.n26.transaction_app.model.TransactionData;
import com.n26.transaction_app.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService subject;

    private Instant now = Instant.now();
    private long timestamp = now.toEpochMilli();
    private long timeStampSecond = now.plus(1000, ChronoUnit.MILLIS).toEpochMilli();
    private long timeStampOld = now.minus(55, ChronoUnit.SECONDS).toEpochMilli();
    private TransactionData firstTransaction = new TransactionData(20, timestamp);
    private TransactionData secondTransaction = new TransactionData(40, timestamp);
    private TransactionData thirdTransaction = new TransactionData(60, timeStampSecond);
    private TransactionData oldTransaction = new TransactionData(60, timeStampOld);

    @Test
    public void shouldSaveTransaction(){
        System.out.println(firstTransaction.getTimestamp());
        subject.saveTransaction(firstTransaction);
        subject.saveTransaction(secondTransaction);
        subject.saveTransaction(thirdTransaction);
        assertEquals(120,subject.getStatistics().getSum(),0.0);
        assertEquals(40,subject.getStatistics().getAvg(),0.0);
        assertEquals(3,subject.getStatistics().getCount(),0.0);
        assertEquals(60,subject.getStatistics().getMax(),0.0);
        assertEquals(20,subject.getStatistics().getMin(),0.0);
    }

    @Test
    public void shouldRemoveOlderTransactions() throws InterruptedException {

        subject.saveTransaction(oldTransaction);
        assertEquals(1l, subject.getStatistics().getCount().longValue());
        Thread.sleep(5000);
        assertEquals(0, subject.getStatistics().getCount().longValue());

    }

}
