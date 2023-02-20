package gr.kyrkosma.service;

import gr.kyrkosma.dto.CustomerDTO;
import gr.kyrkosma.form.CustomerForm;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerForm customerForm);

    List<CustomerDTO> fetchCustomerList();

    CustomerDTO updateCustomer(CustomerForm customerForm, Integer customerId);

    void deleteCustomerById(Integer customerId);

    CustomerDTO getcustomerById(Integer customerId);
}
