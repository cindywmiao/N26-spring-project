package com.example.demo.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class TransactionData {
    private final float amount;
    private final Timestamp timestamp;

    private TransactionData(TransactionDataBuilder builder) {
        this.amount = builder.amount;
        this.timestamp = builder.timestamp;
    }

    public static class TransactionDataBuilder {
        private float amount;
        private Timestamp timestamp;
        public TransactionDataBuilder withAccount(final float account){
            this.amount = account;
            return this;
        }

        public TransactionDataBuilder withTimeStamp(final Timestamp timeStamp){
            this.timestamp = timeStamp;
            return this;
        }

        public TransactionData build(){
            return new TransactionData(this);
        }
    }

    public String toJsonString(){
        return "{\"amount\":" + this.getAmount()+", \"timestamp\":" + this.getTimestamp() + "}";
    }
}
