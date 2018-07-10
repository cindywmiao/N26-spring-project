package com.example.demo.model;

import lombok.Data;

@Data
public class StatisticData {
    private final float sum;
    private final float avg;
    private final float max;
    private final float min;
    private final int count;

    private StatisticData(StatisticDataBuilder builder){
        this.sum = builder.sum;
        this.avg = builder.avg;
        this.max = builder.max;
        this.min = builder.min;
        this.count = builder.count;
    }

    public static class StatisticDataBuilder{
        private float sum;
        private float avg;
        private float max;
        private float min;
        private int count;

        public StatisticDataBuilder withSum(float sum){
            this.sum = sum;
            return this;
        }

        public StatisticDataBuilder withAvg(float avg){
            this.avg = avg;
            return this;
        }

        public StatisticDataBuilder withMax(float max){
            this.max = max;
            return this;
        }

        public StatisticDataBuilder withMin(float min){
            this.min = min;
            return this;
        }

        public StatisticDataBuilder withCount(int count){
            this.count = count;
            return this;
        }

        public StatisticData build(){
            return new StatisticData(this);
        }
    }
}
