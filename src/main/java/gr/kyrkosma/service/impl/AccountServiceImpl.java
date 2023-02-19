package gr.kyrkosma.service.impl;

import gr.kyrkosma.converter.AccountConverter;
import gr.kyrkosma.dto.AccountDTO;
import gr.kyrkosma.entity.Account;
import gr.kyrkosma.entity.Transaction;
import gr.kyrkosma.enums.AccountType;
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
    public AccountDTO saveAccount(AccountForm accountForm) {
        Account newAccount = AccountConverter.convertAccountFormToAccount(accountForm);
        return AccountConverter.convertAccountToAccountDTO(accountRepository.save(newAccount));
    }

    @Override
    public List<Account> fetchAccountList() {
        return accountRepository.findAll();
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

    @Override
    public AccountDTO openAccount(AccountCreationForm accountCreationForm) {

        Account account = new Account();
        account.setBalance(accountCreationForm.getInitialCredit());
        account.setAccountType(AccountType.SAVINGS);
        account.setCustomerId(accountCreationForm.getCustomerID());

        Account accountCreated = accountRepository.save(account);

        if (!accountCreationForm.getInitialCredit().equals(BigDecimal.ZERO)) {
            Transaction transaction = new Transaction(accountCreationForm.getInitialCredit());
            transaction.setAccountId(accountCreated.getAccountId());
            transactionRepository.save(transaction);
        }

        return AccountConverter.convertAccountToAccountDTO(accountCreated);
    }
}
