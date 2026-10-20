package com.pronoleague.backend.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {

    private static final Email EMAIL = new Email("max@test.fr");

    private static User unUserAvecPseudo(String pseudo) {
        return new User(UUID.randomUUID(), EMAIL, pseudo, "hash", Instant.now());
    }

    @Test
    void accepte_un_pseudo_valide() {
        User user = unUserAvecPseudo("max");

        assertEquals("max", user.pseudo());
    }

    @Test
    void retire_les_espaces_autour_du_pseudo() {
        User user = unUserAvecPseudo("  max  ");

        assertEquals("max", user.pseudo());
    }

    @Test
    void accepte_les_longueurs_limites() {
        unUserAvecPseudo("a".repeat(3));  // minimum
        unUserAvecPseudo("a".repeat(20)); // maximum
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",       // vide
            "ab",     // trop court
            "  a ",   // 4 caractères, mais un seul après nettoyage
            "     "   // que des espaces
    })
    void refuse_un_pseudo_trop_court(String pseudo) {
        assertThrows(IllegalArgumentException.class, () -> unUserAvecPseudo(pseudo));
    }

    @Test
    void refuse_un_pseudo_trop_long() {
        assertThrows(IllegalArgumentException.class, () -> unUserAvecPseudo("a".repeat(21)));
    }

    @Test
    void refuse_un_pseudo_null() {
        assertThrows(NullPointerException.class, () -> unUserAvecPseudo(null));
    }

    @Test
    void refuse_les_champs_obligatoires_nuls() {
        assertThrows(NullPointerException.class,
                () -> new User(null, EMAIL, "max", "hash", Instant.now()));
        assertThrows(NullPointerException.class,
                () -> new User(UUID.randomUUID(), null, "max", "hash", Instant.now()));
        assertThrows(NullPointerException.class,
                () -> new User(UUID.randomUUID(), EMAIL, "max", null, Instant.now()));
        assertThrows(NullPointerException.class,
                () -> new User(UUID.randomUUID(), EMAIL, "max", "hash", null));
    }
}
