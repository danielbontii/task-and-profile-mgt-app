package com.danielbontii.tpms.dtos;

import com.danielbontii.tpms.enums.Role;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserCreationInput {

    @Length(min = 1, max = 50, message = "First name must be 1 to 50 characters long")
    private String firstName;

    @Length(min = 1, max = 50, message = "Last name must be 1 to 50 characters long")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "please provide a valid email")
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have 8+ chars, 1+ each of lowercase, uppercase, number, special char"
    )
    private String password;

    //Todo: Add Validation for this
    Role role = Role.USER;
}
