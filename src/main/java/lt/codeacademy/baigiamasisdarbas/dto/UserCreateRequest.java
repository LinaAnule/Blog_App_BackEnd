package lt.codeacademy.baigiamasisdarbas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @NotNull(message = "Cannot be EMPTY")
    private String name;

    @NotNull(message = "Cannot be EMPTY")
    private String lastName;

    @NotNull(message = "Cannot be EMPTY")
    private String email;

    @NotNull(message = "Cannot be EMPTY")
    private LocalDate dateOfBirth;

    @NotNull(message = "Cannot be EMPTY")
    private String username;

    @NotNull(message = "Cannot be EMPTY")
    @Length(min = 8, message = "Password must be at least 8 character long")
    private String password;
}
