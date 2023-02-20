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
public class AccountCreationForm {
    @NotNull
    private BigDecimal initialCredit;

    @Enumerated(EnumType.STRING)
    private AccountType accountType = AccountType.SAVINGS;

    @NotNull
    private Integer customerID;
}
