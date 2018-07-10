package com.example.demo.controller;

import com.example.demo.model.StatisticData;
import com.example.demo.model.TransactionData;
import com.example.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class Controller {

    @Autowired
    private Service service;

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public StatisticData getStatistic() {
        StatisticData result = service.getRealTimeStatistic();
        return result;
    }

    @RequestMapping(value = "/translation",  method = RequestMethod.POST )
    @ResponseBody
    public ResponseEntity<String> property(@RequestBody TransactionData data) {
        if(service.addTransactionData(data)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



}
