package ua.mycompany.util.validator.impl;

import org.springframework.stereotype.Component;
import ua.mycompany.util.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PhoneValidator implements Validator {
    private static final String PHONE_PATTERN = "[0-9]{12}";

    @Override
    public boolean validate(final String hex) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
