package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.junit.Assert.assertNotNull;


import com.example.demo.model.TransactionData;
import com.example.demo.utils.TransactionDataHelper;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
@ContextConfiguration(locations = { "file:./src/main/resources/ApplicationContext.xml" })
public class ControllerTest
{
    private MockMvc mockMvc;
    private TransactionDataHelper transactionDataHelper = new TransactionDataHelper();

    private static final String postUrl = "/transaction";
    private static final String getUrl = "/statistic";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }


    @Test
    public void postTransactionData_ValidData() throws Exception {
        TransactionData transactionData = this.transactionDataHelper.getCurrentTransactionData();
        assertNotNull(transactionData);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.post(postUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new String(transactionData.toJsonString()).getBytes()))
                .andExpect(status().isCreated());


    }

    @Test
    public void postTransactionData_InValidData() throws Exception {
        TransactionData transactionData = this.transactionDataHelper.getOldTransactionData();
        assertNotNull(transactionData);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.post(postUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new String(transactionData.toJsonString()).getBytes()))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getStatistic() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.get(getUrl))
                .andExpect(status().isOk());
    }

}
