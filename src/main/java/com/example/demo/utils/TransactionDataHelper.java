package com.example.demo.utils;

import com.example.demo.model.TransactionData;
import java.sql.Timestamp;
import java.util.Calendar;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

public class TransactionDataHelper {
    private Random random = new Random();
    private static final float min = 1f;
    private static final float max = 5f;
    public static final int sec = -600;

    public TransactionData getCurrentTransactionData() {
        float f = min + random.nextFloat() * (max - min);
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
        Timestamp current = Timestamp.valueOf(ldt);
        return new TransactionData.TransactionDataBuilder().withAccount(f).withTimeStamp(current).build();

    }

    public TransactionData getOldTransactionData(){
        float f = min + random.nextFloat() * (max - min);
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
        Timestamp current = Timestamp.valueOf(ldt);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(current.getTime());
        cal.add(Calendar.SECOND, sec);
        Timestamp oldTime= new Timestamp(cal.getTime().getTime());
        return new TransactionData.TransactionDataBuilder().withAccount(f).withTimeStamp(oldTime).build();
    }


}
