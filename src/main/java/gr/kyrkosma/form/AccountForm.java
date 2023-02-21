package gr.kyrkosma.form;

import gr.kyrkosma.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountForm {

    @NotNull
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType = AccountType.SAVINGS;

    private Integer customerId;
}
