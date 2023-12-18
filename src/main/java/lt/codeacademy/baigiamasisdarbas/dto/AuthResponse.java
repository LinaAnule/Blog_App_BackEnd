package lt.codeacademy.baigiamasisdarbas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.codeacademy.baigiamasisdarbas.Entity.Role;
import java.time.LocalDate;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

        private Long id;
        private String firstName;
        private String lastName;
        private String username;
        private String email;
        private LocalDate dateBirth;
        private String jwtToken;
        private Set<Role> roles;

}
