package gr.kyrkosma.service.impl;

import gr.kyrkosma.converter.CustomerConverter;
import gr.kyrkosma.dto.CustomerDTO;
import gr.kyrkosma.entity.Customer;
import gr.kyrkosma.form.CustomerForm;
import gr.kyrkosma.repository.CustomerRepository;
import gr.kyrkosma.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerDTO saveCustomer(CustomerForm customerForm) {
        Customer newCustomer = CustomerConverter.convertCustomerFormToCustomer(customerForm);
        return CustomerConverter.convertCustomerToCustomerDTO(customerRepository.save(newCustomer));
    }

    @Override
    public List<CustomerDTO> fetchCustomerList() {
        return CustomerConverter.convertCustomerListToCustomerDTOList(customerRepository.findAll());
    }

    @Override
    public CustomerDTO updateCustomer(CustomerForm customerForm, Integer customerId) {
        Customer cust = customerRepository.findCustomerByCustomerId(customerId).orElseThrow(() -> new IllegalStateException("customer not found"));

        cust.setLastName(customerForm.getLastName());

        cust.setFirstName(customerForm.getFirstName());

        return CustomerConverter.convertCustomerToCustomerDTO(customerRepository.save(cust));
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerDTO getcustomerById(Integer customerId) {
        Customer customer = customerRepository.findCustomerByCustomerId(customerId).orElseThrow(() -> new IllegalStateException("customer not found"));
        return CustomerConverter.convertCustomerToCustomerDTO(customer);
    }
}
