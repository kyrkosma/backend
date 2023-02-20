package gr.kyrkosma.converter;

import gr.kyrkosma.dto.AccountDTO;
import gr.kyrkosma.entity.Account;
import gr.kyrkosma.form.AccountCreationForm;
import gr.kyrkosma.form.AccountForm;

import java.util.ArrayList;
import java.util.List;

public class AccountConverter {

    public static AccountDTO convertAccountToAccountDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getAccountId())
                .balance(account.getBalance())
                .accountType(account.getAccountType())
                .transactionList(TransactionConverter.convertTransactionListToTransactionDTOList(account.getTransactionList()))
                .build();
    }

    public static List<AccountDTO> convertAccountListToAccountDTOList(List<Account> accountList) {
        List<AccountDTO> AccountDTOList = new ArrayList<>();
        for (Account account : accountList) {
            AccountDTOList.add(convertAccountToAccountDTO(account));
        }
        return AccountDTOList;
    }

    public static Account convertAccountFormToAccount(AccountForm accountForm) {
        return new Account(accountForm.getBalance(), accountForm.getAccountType(), accountForm.getCustomerId());
    }

    public static Account convertAccountCreationFormToAccount(AccountCreationForm accountCreationForm) {
        return new Account(accountCreationForm.getInitialCredit(), accountCreationForm.getAccountType(), accountCreationForm.getCustomerID());
    }
}
