package com.pronoleague.backend.domain.model;

import java.util.Locale;
import java.util.regex.Pattern;

public record Email(String value) {

    private static final Pattern FORMAT = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");


    public Email {

        if (value == null) {
            throw new IllegalArgumentException("Email invalide");
        }
        value = value.trim().toLowerCase(Locale.ROOT);

        if (!FORMAT.matcher(value).matches()) {
            throw new IllegalArgumentException("Email invalide");
        }

    }

}
