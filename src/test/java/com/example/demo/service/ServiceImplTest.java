package com.example.demo.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import com.example.demo.model.StatisticData;
import com.example.demo.model.TransactionData;
import com.example.demo.utils.TransactionDataHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ServiceImplTest {

    private TransactionDataHelper transactionDataHelper = new TransactionDataHelper();
    private static final float diff = 0.0f;

    @TestConfiguration
    static class ServiceImplTestContextConfiguration {

        @Bean
        public Service service() {
            return new ServiceImpl();
        }
    }

    @Autowired
    private Service mockService;

    @Test
    public void addTransactionData_ValidData(){
        assertNotNull(transactionDataHelper);
        TransactionData transactionData = transactionDataHelper.getCurrentTransactionData();
        assertNotNull(transactionData);
        assertTrue(mockService.addTransactionData(transactionData));
    }

    @Test
    public void addTransactionData_InValidData(){
        assertNotNull(transactionDataHelper);
        TransactionData transactionData = transactionDataHelper.getOldTransactionData();
        assertNotNull(transactionData);
        assertFalse(mockService.addTransactionData(transactionData));
    }

    @Test
    public void getStatisticData_Empty(){
        StatisticData statisticData = mockService.getRealTimeStatistic();
        assertNull(statisticData);
    }

    @Test
    public void getStatisticData_NotEmpty(){
        assertNotNull(transactionDataHelper);
        TransactionData data1 = transactionDataHelper.getCurrentTransactionData();
        TransactionData data2 = transactionDataHelper.getCurrentTransactionData();
        assertNotNull(data1);
        assertNotNull(data2);
        mockService.addTransactionData(data1);
        mockService.addTransactionData(data2);

        StatisticData statisticData = mockService.getRealTimeStatistic();
        assertNotNull(statisticData);
        assertEquals(statisticData.getSum(), data1.getAmount() + data2.getAmount(), diff);
        assertEquals(statisticData.getCount(), 2);
        assertEquals(statisticData.getMax(), Math.max(data1.getAmount(), data2.getAmount()), diff);
        assertEquals(statisticData.getMin(), Math.min(data1.getAmount(), data2.getAmount()), diff);
    }
}
