package gr.kyrkosma.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Integer id;
    private String lastName;
    private String firstName;
    private List<AccountDTO> accountList = new ArrayList<>();
}
