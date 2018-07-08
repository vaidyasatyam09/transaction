package com.n26.transaction_app;

import model.StatisticsData;
import model.TransactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService service;

    @RequestMapping(value="/transactions",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus save(@RequestBody TransactionData data) {
        System.out.println("Amount: "+data.getAmount());
        System.out.println("Input TimeStamp: "+data.getTimeStamp());
        service.saveTransaction(data);
        return HttpStatus.OK;
    }

    @RequestMapping(value="/statistics",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StatisticsData getStatistics() {
        return service.getStatistics();
    }


}