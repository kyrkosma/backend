package gr.kyrkosma.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerForm {

    @Size(max = 30)
    @NotEmpty
    private String lastName;

    @Size(max = 30)
    @NotEmpty
    private String firstName;

    @Size(min = 10, max = 20)
    @NotEmpty
    private String socialSecurityNumber;

}
