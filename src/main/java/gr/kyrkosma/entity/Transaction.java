package gr.kyrkosma.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "amount")
    private BigDecimal amount;

    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JsonBackReference
    private Account account;

    @Column(name = "account_id")
    private Integer accountId;

    public Transaction(BigDecimal amount) {
        this.amount = amount;
    }

    public Transaction(BigDecimal amount, Integer accountId) {
        this.amount = amount;
        this.accountId = accountId;
    }
}
