package com.pronoleague.backend.domain.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record User(UUID id, Email email, String pseudo, String passwordHash, Instant createdAt) {


    public User {

        Objects.requireNonNull(id, "Id Obligatoire");
        Objects.requireNonNull(email, "Email Obligatoire");
        Objects.requireNonNull(pseudo, "Pseudo Obligatoire");
        Objects.requireNonNull(passwordHash, "passewordHash Obligatoire");
        Objects.requireNonNull(createdAt, "createdAt Obligatoire");


        pseudo = pseudo.trim();
        if (pseudo.length() < 3 || pseudo.length() > 20) {
            throw new IllegalArgumentException("Le pseudo doit faire entre 3 et 20 caractéres");
        }
    }
}
