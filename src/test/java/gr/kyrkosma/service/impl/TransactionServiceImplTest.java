package gr.kyrkosma.service.impl;

import gr.kyrkosma.dto.TransactionDTO;
import gr.kyrkosma.entity.Transaction;
import gr.kyrkosma.exception.TransactionAmountIsZeroException;
import gr.kyrkosma.form.TransactionForm;
import gr.kyrkosma.repository.TransactionRepository;
import gr.kyrkosma.service.TransactionService;
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
class TransactionServiceImplTest {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    void test_whenTransactionIsSaved_thenTransactionDTOIsReturned() throws TransactionAmountIsZeroException {

        Transaction transaction = new Transaction(BigDecimal.valueOf(5000), 1);
        transaction.setTransactionId(1);

        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

        TransactionForm transactionForm = TransactionForm.builder()
                .amount(BigDecimal.valueOf(5000))
                .accountId(1)
                .build();

        TransactionDTO transactionDTO = transactionService.saveTransaction(transactionForm);

        Assertions.assertEquals(transactionForm.getAmount(), transactionDTO.getAmount());
    }

    @Test
    void test_whenTransactionListIsFetched_thenTransactionDTOListIsReturned() {
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction;

        for (int i = 0; i < 2; i++) {
            transaction = new Transaction(BigDecimal.valueOf((i + 1) * 1000), 1);
            transactionList.add(transaction);
        }

        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transactionList);

        List<TransactionDTO> transactionDTOList = transactionService.fetchTransactionList();

        for (Transaction trans : transactionList) {
            for (TransactionDTO transDTO : transactionDTOList) {
                if (transDTO.getId().equals(trans.getTransactionId())) {
                    Assertions.assertEquals(transDTO.getAmount(), trans.getAmount());
                }
            }
        }


    }

    @Test
    void test_whenTransactionIsUpdated_thenTransactionDTOIsReturned() {

        TransactionForm transactionForm = TransactionForm.builder()
                .amount(BigDecimal.valueOf(1000))
                .accountId(1)
                .build();

        Transaction transaction = new Transaction(BigDecimal.valueOf(2000), 1);
        transaction.setTransactionId(1);

        Mockito.when(transactionRepository.findTransactionByTransactionId(Mockito.any())).thenReturn(Optional.of(transaction));

        transaction.setAmount(transactionForm.getAmount());

        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

        TransactionDTO transactionDTO = transactionService.updateTransaction(transactionForm, transaction.getTransactionId());

        Assertions.assertEquals(transactionForm.getAmount(), transactionDTO.getAmount());

    }

    @Test
    void test_whenTransactionIsDeletedById_thenTransactionIsDeleted() {
        transactionService.deleteTransactionById(1);
        verify(transactionRepository).deleteById(any());
    }
}