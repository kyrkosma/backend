package gr.kyrkosma.service;

import gr.kyrkosma.dto.AccountDTO;
import gr.kyrkosma.exception.AccountBalanceIsNegativeException;
import gr.kyrkosma.form.AccountCreationForm;
import gr.kyrkosma.form.AccountForm;

import java.util.List;

public interface AccountService {
    AccountDTO saveAccount(AccountCreationForm accountCreationForm) throws AccountBalanceIsNegativeException;

    List<AccountDTO> fetchAccountList();

    AccountDTO updateAccount(AccountForm accountForm, Integer accountId);

    void deleteAccountById(Integer accountId);

}
