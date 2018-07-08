package com.n26.transaction_app;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.TransactionData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionController tansactionController;

    @Test
    public void postTransactionTest() throws Exception {
        TransactionData transaction = new TransactionData(200.0,Instant.now().toEpochMilli());
        ObjectMapper mapper=new ObjectMapper();

        mvc.perform(post("/transactions")
                .content(mapper.writeValueAsString(transaction)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getStatisticsTest() throws Exception{
        mvc.perform(get("/statistics").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
