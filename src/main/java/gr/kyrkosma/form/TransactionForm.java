package gr.kyrkosma.form;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionForm {

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Integer accountId;

}
