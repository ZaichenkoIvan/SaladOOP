package ua.mycompany.helper.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public final class SurnameValidator implements Validator {
    private static final String SURNAME_PATTERN = "[a-zA-Z]{1,}";

    @Override
    public boolean validate(final String hex) {
        Pattern pattern = Pattern.compile(SURNAME_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
