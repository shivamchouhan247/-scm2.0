package com.scm.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Please enter a username")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9._\\- ]{4,19}$", message = "Username must start with a letter, 5-20 characters, [characters, letters, numbers, spaces, '.', '-', '_' allowed]")
    private String name;

    // @NotBlank(message = "Email is required")
    // @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Please pass
    // valid email ")
    // private String email;

    // @NotBlank(message = "password is required")
    // @Pattern(regexp =
    // "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
    // message = "Password must be at least 8 characters long and must contain
    // uppercase, lowercase, digit and special character")
    // private String password;

    // private String about;

    // @NotBlank(message = "Please check the Terms and condition")
    // private String terms;

    // @NotBlank(message = "Username is required")
    // @Size(min=3,message="Min 3 characters is required")
    // private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Min 6 characters is required")
    private String password;

    @NotBlank(message = "About is required")
    private String about;

    @NotBlank(message = "Please check the Terms and condition")
    private String terms;

}
