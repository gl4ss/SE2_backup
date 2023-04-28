package project.ecom.se2_backup.security.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
