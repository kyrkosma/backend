package gr.kyrkosma.service.impl;

import gr.kyrkosma.converter.AccountConverter;
import gr.kyrkosma.dto.AccountDTO;
import gr.kyrkosma.entity.Account;
import gr.kyrkosma.entity.Transaction;
import gr.kyrkosma.form.AccountCreationForm;
import gr.kyrkosma.form.AccountForm;
import gr.kyrkosma.repository.AccountRepository;
import gr.kyrkosma.repository.CustomerRepository;
import gr.kyrkosma.repository.TransactionRepository;
import gr.kyrkosma.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;


    @Override
    public AccountDTO saveAccount(AccountCreationForm accountCreationForm) {

        Account account = new Account();
        account.setBalance(accountCreationForm.getInitialCredit());
        account.setAccountType(accountCreationForm.getAccountType());
        account.setCustomerId(accountCreationForm.getCustomerID());

        Account accountCreated = accountRepository.save(account);

        if (BigDecimal.valueOf(0).compareTo(accountCreationForm.getInitialCredit()) < 0) {
            Transaction transaction = new Transaction(accountCreationForm.getInitialCredit());
            transaction.setAccountId(accountCreated.getAccountId());
            transactionRepository.save(transaction);
        }

        Account newAccount = AccountConverter.convertAccountCreationFormToAccount(accountCreationForm);
        return AccountConverter.convertAccountToAccountDTO(accountRepository.save(newAccount));
    }

    @Override
    public List<AccountDTO> fetchAccountList() {
        return AccountConverter.convertAccountListToAccountDTOList(accountRepository.findAll());
    }

    @Override
    public AccountDTO updateAccount(AccountForm accountForm, Integer accountId) {
        Account acc = accountRepository.findAccountByAccountId(accountId).orElseThrow(() -> new IllegalStateException("account not found"));
        acc.setBalance(accountForm.getBalance());
        acc.setAccountType(accountForm.getAccountType());
        return AccountConverter.convertAccountToAccountDTO(accountRepository.save(acc));
    }

    @Override
    public void deleteAccountById(Integer accountId) {
        accountRepository.deleteById(accountId);
    }
}
