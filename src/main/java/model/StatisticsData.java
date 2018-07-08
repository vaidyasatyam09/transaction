package model;

public class StatisticsData {
    private double sum;
    private double avg;
    private double max;
    private double min;
    private long count;

    public StatisticsData() {
        System.out.println("no data for stats");
    }

    public StatisticsData(TransactionData data){
        this.sum = data.getAmount();
        this.avg = data.getAmount();
        this.max = data.getAmount();
        this.min = data.getAmount();
        this.count = 1;
    }
    public StatisticsData(double sum, double avg, double max, double min, long count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public long getCount() {
        return count;
    }

    public void updateStatistics(StatisticsData statisticsData){

        this.avg = ((statisticsData.avg * statisticsData.count) + (this.avg * this.count)) / (statisticsData.count + this.count);
        this.sum = statisticsData.sum + this.sum;
        this.count = statisticsData.count + this.count;
        this.max = Math.max(this.max, statisticsData.max);
        this.min = Math.min(this.min, statisticsData.min);
    }
}
