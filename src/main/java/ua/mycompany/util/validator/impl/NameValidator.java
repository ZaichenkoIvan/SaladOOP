package ua.mycompany.util.validator.impl;

import org.springframework.stereotype.Component;
import ua.mycompany.util.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class NameValidator implements Validator {
    private static final String NAME_PATTERN = "[a-zA-Z]{2,}";

    @Override
    public boolean validate(final String hex) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
