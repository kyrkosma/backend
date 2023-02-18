package gr.kyrkosma.dto;

import gr.kyrkosma.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Integer id;
    private BigDecimal balance;
    private AccountType accountType;
    private List<TransactionDTO> transactionList;

}
