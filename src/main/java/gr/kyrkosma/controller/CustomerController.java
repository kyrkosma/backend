package gr.kyrkosma.controller;

import gr.kyrkosma.dto.CustomerDTO;
import gr.kyrkosma.form.CustomerForm;
import gr.kyrkosma.service.AccountService;
import gr.kyrkosma.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/customers")
    public List<CustomerDTO> fetchCustomerList() {
        return customerService.fetchCustomerList();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO fetchCustomerList(@PathVariable("id") Integer customerId) {
        return customerService.getcustomerById(customerId);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@Valid @RequestBody CustomerForm customerForm) {
        return customerService.saveCustomer(customerForm);
    }

    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@Valid @RequestBody CustomerForm customerForm, @PathVariable("id") Integer customerId) {
        return customerService.updateCustomer(customerForm, customerId);
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomerById(@PathVariable("id") Integer customerId) {
        customerService.deleteCustomerById(customerId);
        return "Deleted Successfully";
    }
}
