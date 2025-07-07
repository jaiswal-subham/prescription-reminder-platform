package com.example.prescription.reminder.demo.utilities;

import lombok.Data;

@Data
public class ConstantsValues {
    public static final String userNotFoundErrorMessage = "User not found with username:";

    public static final String loginRoute = "/login";

    public static final String registerRoute = "/register";

    public static final String userGenericRoute = "/api/prescription/*";

    public static final String userRole = "user";

    public static final String loginFailed = "Login failed";

    public static final String registrationFailed = "Registration failed";

    public static final String usernameIsRequired = "username is required";

    public static final String passwordIsRequired = "Password is required";

    public static final String emailIdIsRequired = "emailId is required";

    public static final String firstNameIsRequired = "firstName is required";

    public static final String success = "Success";

}
