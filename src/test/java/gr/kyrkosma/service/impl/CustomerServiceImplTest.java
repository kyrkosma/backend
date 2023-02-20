package gr.kyrkosma.service.impl;

import gr.kyrkosma.dto.CustomerDTO;
import gr.kyrkosma.entity.Customer;
import gr.kyrkosma.form.CustomerForm;
import gr.kyrkosma.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerServiceImpl customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void test_whenCustomerIsSaved_thenCustomerDTOIsReturned() {

        Customer customer = new Customer("Kyrkos", "Marios", "SSN-1234-3214");
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);

        CustomerForm customerForm = CustomerForm.builder()
                .lastName("Kyrkos")
                .firstName("Marios")
                .socialSecurityNumber("SSN-1234-3214")
                .build();

        CustomerDTO customerDTO = customerService.saveCustomer(customerForm);

        Assertions.assertEquals(customerForm.getLastName(), customerDTO.getLastName());
        Assertions.assertEquals(customerForm.getFirstName(), customerDTO.getFirstName());
    }

    @Test
    void test_whenCustomerListIsFetched_thenCustomerDTOListIsReturned() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("Kyrkos", "Marios", "SSN-1234-3214"));
        customerList.add(new Customer("Doe", "John", "SSN-1234-5555"));

        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customerList);

        List<CustomerDTO> customerDTOList = customerService.fetchCustomerList();

        for (Customer cust : customerList) {
            for (CustomerDTO custDTO : customerDTOList) {
                if (custDTO.getId().equals(cust.getCustomerId())) {
                    Assertions.assertEquals(custDTO.getLastName(), cust.getLastName());
                    Assertions.assertEquals(custDTO.getFirstName(), cust.getFirstName());
                }
            }
        }
    }

    @Test
    void test_whenCustomerIsUpdated_thenCustomerDTOIsReturned() {

        CustomerForm customerForm = CustomerForm.builder()
                .lastName("Kyrkos")
                .firstName("Marios")
                .socialSecurityNumber("SSN-1234-3214")
                .build();

        Customer customer = new Customer("Potter", "Harry", "SSN-1234-2222");
        customer.setCustomerId(1);

        Mockito.when(customerRepository.findCustomerByCustomerId(Mockito.any())).thenReturn(Optional.of(customer));

        customer.setLastName("Potter");
        customer.setFirstName("Harry");

        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);

        CustomerDTO customerDTO = customerService.updateCustomer(customerForm, customer.getCustomerId());

        Assertions.assertEquals(customerForm.getLastName(), customerDTO.getLastName());
        Assertions.assertEquals(customerForm.getFirstName(), customerDTO.getFirstName());

    }

    @Test
    void test_whenCustomerIsDeletedById_thenCustomerIsDeleted() {
        customerService.deleteCustomerById(1);
        verify(customerRepository).deleteById(any());
    }

    @Test
    void test_whenCustomerIsFetched_thenCustomerDTOIsReturned() {
        Customer customer = new Customer("Potter", "Harry", "SSN-1234-2222");
        customer.setCustomerId(1);

        Mockito.when(customerRepository.findCustomerByCustomerId(Mockito.any())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getcustomerById(1);

        Assertions.assertEquals(customer.getLastName(), customerDTO.getLastName());
        Assertions.assertEquals(customer.getFirstName(), customerDTO.getFirstName());
    }
}