package gr.kyrkosma.converter;

import gr.kyrkosma.dto.CustomerDTO;
import gr.kyrkosma.entity.Customer;
import gr.kyrkosma.form.CustomerForm;

import java.util.ArrayList;
import java.util.List;

public class CustomerConverter {
    public static CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .accountList(AccountConverter.convertAccountListToAccountDTOList(customer.getAccountList()))
                .build();
    }

    public static List<CustomerDTO> convertCustomerListToCustomerDTOList(List<Customer> customerList) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDTOList.add(convertCustomerToCustomerDTO(customer));
        }
        return customerDTOList;
    }

    public static Customer convertCustomerFormToCustomer(CustomerForm customerForm) {
        return new Customer(customerForm.getLastName(), customerForm.getFirstName(), customerForm.getSocialSecurityNumber());
    }
}
