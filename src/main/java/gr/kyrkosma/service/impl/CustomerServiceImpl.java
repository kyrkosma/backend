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
import java.util.Objects;

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
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Integer customerId) {
        Customer cust = customerRepository.findCustomerByCustomerId(customerId).orElseThrow(() -> new IllegalStateException("customer not found"));

        if (Objects.nonNull(customerDTO.getLastName()) && !"".equalsIgnoreCase(customerDTO.getLastName())) {
            cust.setLastName(customerDTO.getLastName());
        }

        if (Objects.nonNull(customerDTO.getFirstName()) && !"".equalsIgnoreCase(customerDTO.getFirstName())) {
            cust.setFirstName(customerDTO.getFirstName());
        }

        return CustomerConverter.convertCustomerToCustomerDTO(customerRepository.save(cust));
    }

    @Override
    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

}
