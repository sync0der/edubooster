package uz.tsue.ricoin.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    @NotEmpty(message = "{application.validation.constraint.email.NotEmpty}")
    @NotNull(message = "{application.validation.constraint.email.NotNull}")
    @Email(message = "")
    private String email;

    @Size(min = 8, message = "")
    @NotEmpty(message = "{application.validation.constraint.email.NotEmpty}")
    private String password;

    //    @Pattern(regexp = "^[a-zA-Z]+$", message = "{application.validation.constraint.firstName.Pattern}")
    private String firstName;

    //    @Pattern(regexp = "^[a-zA-Z]+$", message = "{application.validation.constraint.lastName.Pattern}")
    private String lastName;

    //    @Size(min = 2, max = 50, message = "{application.validation.constraint.groupName.Size}")
    private String groupName;

    //    @Pattern(regexp = "^\\+998[0-9]{2}[0-9]{7}$", message = "{application.validation.constraint.phoneNumber.Pattern}")
    private String phoneNumber;

}
