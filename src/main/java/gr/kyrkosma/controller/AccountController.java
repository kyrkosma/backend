package gr.kyrkosma.controller;

import gr.kyrkosma.converter.AccountConverter;
import gr.kyrkosma.dto.AccountDTO;
import gr.kyrkosma.form.AccountCreationForm;
import gr.kyrkosma.form.AccountForm;
import gr.kyrkosma.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountDTO> fetchAccountList() {
        return AccountConverter.convertAccountListToAccountDTOList(accountService.fetchAccountList());
    }

    @PostMapping("/accounts")
    public AccountDTO saveAccount(@Valid @RequestBody AccountForm accountForm) {
        return accountService.saveAccount(accountForm);
    }

    @PutMapping("/accounts/{id}")
    public AccountDTO updateAccount(@RequestBody AccountForm accountForm, @PathVariable("id") Integer accountId) {
        return accountService.updateAccount(accountForm, accountId);
    }

    @DeleteMapping("/accounts/{id}")
    public String deleteAccountById(@PathVariable("id") Integer accountId) {
        accountService.deleteAccountById(accountId);
        return "Deleted Successfully";
    }

    @PostMapping(value = "/accounts/new")
    public AccountDTO openCustomerAccount(@RequestBody AccountCreationForm accountCreationForm) {
        return accountService.openAccount(accountCreationForm);
    }

}
