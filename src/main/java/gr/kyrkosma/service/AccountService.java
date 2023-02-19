package gr.kyrkosma.service;

import gr.kyrkosma.dto.AccountDTO;
import gr.kyrkosma.entity.Account;
import gr.kyrkosma.form.AccountCreationForm;
import gr.kyrkosma.form.AccountForm;

import java.util.List;

public interface AccountService {
    AccountDTO saveAccount(AccountForm accountForm);

    List<Account> fetchAccountList();

    AccountDTO updateAccount(AccountForm accountForm, Integer accountId);

    void deleteAccountById(Integer accountId);

    AccountDTO openAccount(AccountCreationForm accountCreationForm);
}
