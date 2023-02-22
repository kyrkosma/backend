package gr.kyrkosma.service;

import gr.kyrkosma.dto.TransactionDTO;
import gr.kyrkosma.exception.TransactionAmountIsZeroException;
import gr.kyrkosma.form.TransactionForm;

import java.util.List;

public interface TransactionService {
    TransactionDTO saveTransaction(TransactionForm transactionForm) throws TransactionAmountIsZeroException;

    List<TransactionDTO> fetchTransactionList();

    TransactionDTO updateTransaction(TransactionForm transactionForm, Integer transactionId);

    void deleteTransactionById(Integer transactionId);
}
