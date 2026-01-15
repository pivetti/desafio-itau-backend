package com.example.desafio_itau_3.dto;

import java.util.DoubleSummaryStatistics;

public class StatisticsResponse {
    
    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    public StatisticsResponse(DoubleSummaryStatistics stats){
        this.count = stats.getCount();
        this.sum = stats.getSum();

        if (count == 0) {
            this.avg = 0.0;
            this.min = 0.0;
            this.max = 0.0;
        } else {
            this.avg = stats.getAverage();
            this.min = stats.getMin();
            this.max = stats.getMax();
        }
    }

    public long getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }
    
    public double getAvg() {
        return avg;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
