package ua.mycompany.helper.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.customer.Customer;

@Component
public class UserValidator {
    private EmailValidator emailValidator;
    private NameValidator nameValidator;
    private PhoneValidator phoneValidator;
    private SurnameValidator surnameValidator;

    @Autowired
    public UserValidator(EmailValidator emailValidator, NameValidator nameValidator,
                         PhoneValidator phoneValidator, SurnameValidator surnameValidator) {
        this.emailValidator = emailValidator;
        this.nameValidator = nameValidator;
        this.phoneValidator = phoneValidator;
        this.surnameValidator = surnameValidator;
    }

    public boolean validate(Customer customer) {
        return emailValidator.validate(customer.getEmail()) &&
                nameValidator.validate(customer.getName()) &&
                phoneValidator.validate(customer.getPhoneNumber()) &&
                surnameValidator.validate(customer.getSurname());
    }
}
