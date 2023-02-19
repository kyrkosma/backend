package gr.kyrkosma.repository;

import gr.kyrkosma.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<Transaction> findTransactionByTransactionId(Integer id);
}
