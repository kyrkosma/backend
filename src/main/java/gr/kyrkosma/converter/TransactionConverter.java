package gr.kyrkosma.converter;

import gr.kyrkosma.dto.TransactionDTO;
import gr.kyrkosma.entity.Transaction;
import gr.kyrkosma.form.TransactionForm;

import java.util.ArrayList;
import java.util.List;

public class TransactionConverter {
    public static TransactionDTO convertTransactionToTransactionDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getTransactionId())
                .amount(transaction.getAmount())
                .build();
    }

    public static List<TransactionDTO> convertTransactionListToTransactionDTOList(List<Transaction> transactionList) {
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            transactionDTOList.add(convertTransactionToTransactionDTO(transaction));
        }
        return transactionDTOList;
    }

    public static Transaction convertTransactionFormToTransaction(TransactionForm transactionForm) {
        return new Transaction(transactionForm.getAmount(), transactionForm.getAccountId());
    }
}
