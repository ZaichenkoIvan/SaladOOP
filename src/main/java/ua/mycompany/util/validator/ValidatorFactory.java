package ua.mycompany.util.validator;

import org.springframework.stereotype.Component;
import ua.mycompany.util.validator.impl.*;

@Component
public final class ValidatorFactory {
    private ValidatorFactory() {
    }

    public static Validator getValidator(String field) {
        switch (field) {
            case "email":
                return new EmailValidator();
            case "name":
                return new NameValidator();
            case "surname":
                return new SurnameValidator();
            case "phoneNumber":
                return new PhoneValidator();
            case "date":
                return new DateValidator();
        }
        throw new IllegalArgumentException();
    }

}
