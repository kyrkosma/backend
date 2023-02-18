package gr.kyrkosma.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import gr.kyrkosma.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    @JsonBackReference
    private Customer customer;

    @Column(name = "customer_id")
    private Integer customerId;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Transaction> transactionList = new ArrayList<>();

    public Account(BigDecimal balance, AccountType accountType, Integer customerId) {
        this.balance = balance;
        this.accountType = accountType;
        this.customerId = customerId;
    }
}
