package ua.mycompany.util.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.util.validator.impl.EmailValidator;
import ua.mycompany.util.validator.impl.NameValidator;
import ua.mycompany.util.validator.impl.PhoneValidator;
import ua.mycompany.util.validator.impl.SurnameValidator;

@Component
public class UserValidator {
    private static final Logger logger = Logger.getLogger(UserValidator.class);
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
        boolean validate = emailValidator.validate(customer.getEmail()) &&
                nameValidator.validate(customer.getName()) &&
                phoneValidator.validate(customer.getPhoneNumber()) &&
                surnameValidator.validate(customer.getSurname());
        if (validate) {
            logger.info("Customer validate successful");
        } else {
            logger.error("Validate error in UserValidator!!!");
        }

        return validate;
    }
}
