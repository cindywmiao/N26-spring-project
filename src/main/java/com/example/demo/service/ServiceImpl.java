package com.example.demo.service;

import com.example.demo.model.StatisticData;
import com.example.demo.model.TransactionData;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{
    public final static int capacity = 100001;
    Deque<TransactionData> deque = new LinkedList<>();

    @Override
    public StatisticData getRealTimeStatistic(){
        if(deque.isEmpty()) return null;
        Iterator<TransactionData> it = deque.descendingIterator();
        float sum = 0, max = Float.MIN_VALUE, min = Float.MAX_VALUE;
        int count = 0;
        while(it.hasNext()){
            TransactionData curr = it.next();
            if(!this.isValidTransactionData(curr)) break;
            else{
                sum += curr.getAmount();
                max = Math.max(max, curr.getAmount());
                min = Math.min(min, curr.getAmount());
                count++;
            }
        }
        float avg = sum/count;
        return new StatisticData.StatisticDataBuilder()
                .withSum(sum)
                .withAvg(avg)
                .withMax(max)
                .withMin(min)
                .withCount(count)
                .build();
    }

    @Override
    public boolean addTransactionData(TransactionData transactionData){
        if(this.isValidTransactionData(transactionData)) {
            if (deque.size() == capacity) {
                deque.removeFirst();
            }
            deque.addLast(transactionData);
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isValidTransactionData(TransactionData transactionData){
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
        Timestamp current = Timestamp.valueOf(ldt);
        System.out.println(current.getTime());
        long diff = Math.abs(current.getTime() - transactionData.getTimestamp().getTime()) / 1000;

        return diff <= 60;
    }
}
