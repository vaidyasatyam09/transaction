package com.n26.transaction_app.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import static java.lang.System.currentTimeMillis;

public class TransactionData {
    public static final int TIME_LIMIT = 60000;
    private double amount;
    private long timestamp;

    public TransactionData() {
    }

    public TransactionData(double amount, long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @JsonIgnore
    public boolean isWithinTimeLimit() {
        long timeDiff = currentTimeMillis() - timestamp;
        return ( timeDiff<= TIME_LIMIT) && (0<timeDiff);
    }

}
