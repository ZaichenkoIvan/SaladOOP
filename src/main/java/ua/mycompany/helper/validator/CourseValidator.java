package ua.mycompany.helper.validator;


import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CourseValidator implements Validator {
    private static final String NAME_PATTERN = "[1-6]{1}";

    @Override
    public boolean validate(final String hex) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
