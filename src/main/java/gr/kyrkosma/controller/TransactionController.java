package gr.kyrkosma.controller;

import gr.kyrkosma.dto.TransactionDTO;
import gr.kyrkosma.form.TransactionForm;
import gr.kyrkosma.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public List<TransactionDTO> fetchTransactionList() {
        return transactionService.fetchTransactionList();
    }

    @PostMapping("/transactions")
    public TransactionDTO saveTransaction(@Valid @RequestBody TransactionForm transactionForm) {
        return transactionService.saveTransaction(transactionForm);
    }

    @PutMapping("/transactions/{id}")
    public TransactionDTO updateTransaction(@RequestBody TransactionForm transactionForm, @PathVariable("id") Integer transactionId) {
        return transactionService.updateTransaction(transactionForm, transactionId);
    }

    @DeleteMapping("/transactions/{id}")
    public String deleteTransactionById(@PathVariable("id") Integer transactionId) {
        transactionService.deleteTransactionById(transactionId);
        return "Deleted Successfully";
    }

}
