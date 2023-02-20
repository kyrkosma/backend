package gr.kyrkosma.service.impl;

import gr.kyrkosma.dto.AccountDTO;
import gr.kyrkosma.entity.Account;
import gr.kyrkosma.enums.AccountType;
import gr.kyrkosma.form.AccountCreationForm;
import gr.kyrkosma.form.AccountForm;
import gr.kyrkosma.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    private AccountServiceImpl accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    void test_whenAccountIsSaved_thenAccountDTOIsReturned() {

        Account account = new Account(BigDecimal.valueOf(1000), AccountType.INVESTMENT, 2);
        account.setAccountId(1);

        Mockito.when(accountRepository.save(any())).thenReturn(account);

        AccountCreationForm accountCreationForm = AccountCreationForm.builder()
                .initialCredit(BigDecimal.valueOf(1000))
                .accountType(AccountType.INVESTMENT)
                .customerID(1)
                .build();

        AccountDTO accountDTO = accountService.saveAccount(accountCreationForm);

        Assertions.assertEquals(accountCreationForm.getAccountType(), accountDTO.getAccountType());
    }

    @Test
    void test_whenAccountListIsFetched_thenAccountDTOListIsReturned() {

        List<Account> accountList = new ArrayList<>();
        Account account;

        for (int i = 0; i < 2; i++) {
            int remainder = i % 2;
            account = new Account(BigDecimal.valueOf((i + 1) * 1000), remainder > 0 ? AccountType.INVESTMENT : AccountType.SAVINGS, 2);
            accountList.add(account);
        }

        Mockito.when(accountRepository.save(any())).thenReturn(accountList);

        List<AccountDTO> accountDTOList = accountService.fetchAccountList();

        for (Account acc : accountList) {
            for (AccountDTO accDTO : accountDTOList) {
                if (accDTO.getId().equals(acc.getAccountId())) {
                    Assertions.assertEquals(accDTO.getAccountType(), acc.getAccountType());
                    Assertions.assertEquals(accDTO.getBalance(), acc.getBalance());
                }
            }
        }

    }

    @Test
    void test_whenAccountIsUpdated_thenAccountDTOIsReturned() {

        AccountForm accountForm = AccountForm.builder()
                .balance(BigDecimal.valueOf(5000))
                .accountType(AccountType.SAVINGS)
                .customerId(1)
                .build();

        Account account = new Account(BigDecimal.valueOf(1000), AccountType.INVESTMENT, 2);
        account.setAccountId(1);

        Mockito.when(accountRepository.findAccountByAccountId(any())).thenReturn(Optional.of(account));

        account.setBalance(accountForm.getBalance());
        account.setAccountType(accountForm.getAccountType());

        Mockito.when(accountRepository.save(any())).thenReturn(account);

        AccountDTO accountDTO = accountService.updateAccount(accountForm, account.getAccountId());

        Assertions.assertEquals(accountForm.getAccountType(), accountDTO.getAccountType());
        Assertions.assertEquals(accountForm.getBalance(), accountDTO.getBalance());

    }

    @Test
    void test_whenAccountIsDeletedById_thenAccountIsDeleted() {
        accountService.deleteAccountById(1);
        verify(accountRepository).deleteById(any());
    }

}