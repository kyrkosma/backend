package gr.kyrkosma.controller;

import com.google.gson.Gson;
import gr.kyrkosma.BackendApplication;
import gr.kyrkosma.converter.AccountConverter;
import gr.kyrkosma.dto.AccountDTO;
import gr.kyrkosma.entity.Account;
import gr.kyrkosma.enums.AccountType;
import gr.kyrkosma.form.AccountCreationForm;
import gr.kyrkosma.form.AccountForm;
import gr.kyrkosma.repository.AccountRepository;
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
public class AccountRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountRepository accountRepository;

    private static int accountId;

    @Test
    @Order(1)
    public void test_whenGetAccounts_thenStatus200() throws Exception {

        AccountForm accountForm = AccountForm.builder()
                .balance(BigDecimal.valueOf(5000))
                .accountType(AccountType.SAVINGS)
                .customerId(1)
                .build();
        Account account = AccountConverter.convertAccountFormToAccount(accountForm);
        AccountDTO accountDTO = AccountConverter.convertAccountToAccountDTO(accountRepository.save(account));
        accountId = accountDTO.getId();

        mvc.perform(get("/accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[" + (accountId - 1) + "].balance").value(5000));

    }

    @Test
    @Order(2)
    public void test_whenPostAccounts_thenStatus200() throws Exception {

        AccountCreationForm accountCreationForm = AccountCreationForm.builder()
                .initialCredit(BigDecimal.valueOf(5000))
                .accountType(AccountType.SAVINGS)
                .customerID(1)
                .build();

        String accountFormJson = new Gson().toJson(accountCreationForm);

        mvc.perform(post("/accounts")
                        .content(accountFormJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("balance").value("5000"))
                .andExpect(jsonPath("accountType").value("SAVINGS"));
    }

    @Test
    @Order(3)
    public void test_whenPutAccounts_thenStatus200() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accountType", "INVESTMENT");
        jsonObject.put("balance", "135000");

        mvc.perform(put("/accounts/" + accountId)
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("balance").value("135000"))
                .andExpect(jsonPath("accountType").value("INVESTMENT"));

    }

    @Test
    @Order(4)
    public void test_whenDeleteAccounts_thenStatus200() throws Exception {

        mvc.perform(delete("/accounts/" + accountId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Deleted Successfully"));
    }

}
