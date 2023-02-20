package gr.kyrkosma.controller;

import com.google.gson.Gson;
import gr.kyrkosma.BackendApplication;
import gr.kyrkosma.converter.TransactionConverter;
import gr.kyrkosma.dto.TransactionDTO;
import gr.kyrkosma.entity.Transaction;
import gr.kyrkosma.form.TransactionForm;
import gr.kyrkosma.repository.TransactionRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BackendApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(
        locations = "classpath:application.properties")
public class TransactionRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TransactionRepository transactionRepository;

    private static int transactionId;

    @Test
    @Order(1)
    public void test_whenGetTransactions_thenStatus200() throws Exception {

        TransactionForm transactionForm = TransactionForm.builder()
                .amount(BigDecimal.valueOf(1000))
                .accountId(1).build();
        Transaction transaction = TransactionConverter.convertTransactionFormToTransaction(transactionForm);
        TransactionDTO transactionDTO = TransactionConverter.convertTransactionToTransactionDTO(transactionRepository.save(transaction));
        transactionId = transactionDTO.getId();

        mvc.perform(get("/transactions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[" + (transactionId - 1) + "].amount").value(1000));

    }

    @Test
    @Order(2)
    public void test_whenPostTransactions_thenStatus200() throws Exception {

        TransactionForm transactionForm = TransactionForm.builder()
                .amount(BigDecimal.valueOf(100000))
                .accountId(1)
                .build();

        String transactionFormJson = new Gson().toJson(transactionForm);

        mvc.perform(post("/transactions")
                        .content(transactionFormJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("amount").value("100000"));

    }

    @Test
    @Order(3)
    public void test_whenPutTransactions_thenStatus200() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("amount", "5000000");

        mvc.perform(put("/transactions/" + transactionId)
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("amount").value("5000000"));

    }

    @Test
    @Order(4)
    public void test_whenDeleteTransactions_thenStatus200() throws Exception {

        mvc.perform(delete("/transactions/" + transactionId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Deleted Successfully"));

    }


}
