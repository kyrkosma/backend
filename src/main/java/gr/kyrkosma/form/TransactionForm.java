package gr.kyrkosma.form;

import jakarta.validation.constraints.Min;
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
    @Min(1)
    private BigDecimal amount;

    private Integer accountId;

}
