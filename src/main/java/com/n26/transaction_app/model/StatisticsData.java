package com.n26.transaction_app.model;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
public class StatisticsData {
    private Double sum=0.0;
    private Double avg=0.0;
    private Double max=0.0;
    private Double min=0.0;
    private Long count=0l;

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public StatisticsData(Collection<TransactionData> transactions) {
        final List<Double> amounts = transactions.stream().map(TransactionData::getAmount).collect(toList());
        final Long count = amounts.stream().count();
        if (count > 0) {
            this.setCount(count);
            this.setSum(amounts.stream().mapToDouble(Double::doubleValue).sum());
            this.setAvg(amounts.stream().mapToDouble(Double::doubleValue).average().getAsDouble());
            this.setMax(amounts.stream().max(Double::compareTo).get());
            this.setMin(amounts.stream().min(Double::compareTo).get());
        }
    }
}
