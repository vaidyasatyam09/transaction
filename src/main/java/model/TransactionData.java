package model;

public class TransactionData {
    private double amount;
    private long timeStamp;

    public TransactionData() {
    }

    public TransactionData(double amount, long timeStamp) {
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }


}
