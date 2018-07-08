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
        if(data.getTimestamp()==0)
            return HttpStatus.PARTIAL_CONTENT;
        if(!data.isWithinTimeLimit())
            return HttpStatus.NO_CONTENT;
        service.saveTransaction(data);
        return HttpStatus.CREATED;
    }

    @RequestMapping(value="/statistics",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public StatisticsData getStatistics() {
        return service.getStatistics();
    }
}