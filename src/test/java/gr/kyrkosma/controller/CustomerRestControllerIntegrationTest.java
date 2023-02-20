package gr.kyrkosma.controller;

import com.google.gson.Gson;
import gr.kyrkosma.BackendApplication;
import gr.kyrkosma.converter.CustomerConverter;
import gr.kyrkosma.dto.CustomerDTO;
import gr.kyrkosma.entity.Customer;
import gr.kyrkosma.form.CustomerForm;
import gr.kyrkosma.repository.CustomerRepository;
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
public class CustomerRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerRepository customerRepository;

    private static int customerId;

    @Test
    @Order(1)
    public void test_whenGetCustomers_thenStatus200() throws Exception {

        CustomerForm customerForm = CustomerForm.builder()
                .lastName("Hello")
                .firstName("World")
                .socialSecurityNumber("SSN-1234-3214")
                .build();
        Customer customer = CustomerConverter.convertCustomerFormToCustomer(customerForm);
        CustomerDTO customerDTO = CustomerConverter.convertCustomerToCustomerDTO(customerRepository.save(customer));
        customerId = customerDTO.getId();

        mvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[" + (customerId - 1) + "].lastName").value("Hello"))
                .andExpect(jsonPath("$[" + (customerId - 1) + "].firstName").value("World"));
    }

    @Test
    @Order(2)
    public void test_whenPostCustomers_thenStatus200() throws Exception {
        CustomerForm customerForm = CustomerForm.builder()
                .lastName("Hello")
                .firstName("World")
                .socialSecurityNumber("SSN-1234-3214")
                .build();

        String customerFormJson = new Gson().toJson(customerForm);

        mvc.perform(post("/customers")
                        .content(customerFormJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("lastName").value("Hello"));

    }

    @Test
    @Order(3)
    public void test_whenPutCustomers_thenStatus200() throws Exception {


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lastName", "Doe");
        jsonObject.put("firstName", "John");
        jsonObject.put("socialSecurityNumber", "SSN-5555-5123");

        mvc.perform(put("/customers/" + customerId)
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("lastName").value("Doe"))
                .andExpect(jsonPath("firstName").value("John"));
    }

    @Test
    @Order(4)
    public void test_whenDeleteCustomers_thenStatus200() throws Exception {

        mvc.perform(delete("/customers/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Deleted Successfully"));

    }

}
