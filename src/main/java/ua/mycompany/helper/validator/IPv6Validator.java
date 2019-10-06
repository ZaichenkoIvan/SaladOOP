package ua.mycompany.helper.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public final class IPv6Validator implements Validator {
    private static final String IPV6_PATTERN = "((^|:)([0-9a-fA-F]{0,4})){1,8}$";

    @Override
    public boolean validate(final String hex) {
        Pattern pattern = Pattern.compile(IPV6_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}

