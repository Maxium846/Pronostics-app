package com.pronoleague.backend.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;


class EmailTest {

    @Test
    void accepte_un_email_valide() {
        Email email = new Email("max@test.fr");

        assertEquals("max@test.fr", email.value());
    }

    @Test
    void normalise_la_casse_et_retire_les_espaces_autour() {
        Email email = new Email("  Max@Test.FR ");

        assertEquals("max@test.fr", email.value());
    }

    @Test
    void deux_emails_de_meme_valeur_sont_egaux() {
        assertEquals(new Email("max@test.fr"), new Email(" MAX@test.fr"));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "",              // vide
            "   ",           // que des espaces
            "@",             // un arobase seul
            "max@",          // rien après l'arobase
            "@test.fr",      // rien avant l'arobase
            "max@test",      // pas de point dans le domaine
            "max test@a.fr", // espace dans l'adresse
            "max@@test.fr"   // deux arobases
    })
    void refuse_un_email_invalide(String valeur) {
        assertThrows(IllegalArgumentException.class, () -> new Email(valeur));
    }
}
