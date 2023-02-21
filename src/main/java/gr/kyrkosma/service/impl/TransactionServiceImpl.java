package gr.kyrkosma.service.impl;

import gr.kyrkosma.converter.TransactionConverter;
import gr.kyrkosma.dto.TransactionDTO;
import gr.kyrkosma.entity.Transaction;
import gr.kyrkosma.exception.TransactionAmountIsZeroException;
import gr.kyrkosma.form.TransactionForm;
import gr.kyrkosma.repository.TransactionRepository;
import gr.kyrkosma.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionDTO saveTransaction(TransactionForm transactionForm) {
        if (BigDecimal.valueOf(0).equals(transactionForm.getAmount())) {
            throw new TransactionAmountIsZeroException();
        }
        Transaction newTransaction = TransactionConverter.convertTransactionFormToTransaction(transactionForm);
        return TransactionConverter.convertTransactionToTransactionDTO(transactionRepository.save(newTransaction));
    }

    @Override
    public List<TransactionDTO> fetchTransactionList() {
        return TransactionConverter.convertTransactionListToTransactionDTOList(transactionRepository.findAll());
    }

    @Override
    public TransactionDTO updateTransaction(TransactionForm transactionForm, Integer transactionId) {
        Transaction trans = transactionRepository.findTransactionByTransactionId(transactionId).orElseThrow(() -> new IllegalStateException("transaction not found"));
        trans.setAmount(transactionForm.getAmount());
        return TransactionConverter.convertTransactionToTransactionDTO(transactionRepository.save(trans));

    }

    @Override
    public void deleteTransactionById(Integer transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
