package gr.kyrkosma.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerForm {

    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][-a-zA-Z]+$", message = "lastName is mandatory")
    @NotNull
    private String lastName;

    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][-a-zA-Z]+$", message = "firstName is mandatory")
    @NotNull
    private String firstName;

    @Size(min = 10, max = 20)
    @NotBlank
    private String socialSecurityNumber;

}
