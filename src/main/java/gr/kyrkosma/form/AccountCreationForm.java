package gr.kyrkosma.form;

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

    @NotNull
    private Integer customerID;
}
