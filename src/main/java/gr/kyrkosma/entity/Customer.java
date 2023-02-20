package gr.kyrkosma.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Size(max = 30)
    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 30)
    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 10, max = 20)
    @NotNull
    @Column(name = "social_security_number")
    private String socialSecurityNumber;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Account> accountList = new ArrayList<>();

    public Customer(String lastName, String firstName, String socialSecurityNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
