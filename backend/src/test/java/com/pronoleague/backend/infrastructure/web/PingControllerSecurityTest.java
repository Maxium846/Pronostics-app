package com.pronoleague.backend.infrastructure.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.pronoleague.backend.infrastructure.security.SecurityConfig;

/**
 * Test de la couche web uniquement : pas de base de données, pas de Spring complet.
 * Il vérifie nos règles de sécurité (SecurityConfig) sur la route de test.
 */
@WebMvcTest(PingController.class)
@Import(SecurityConfig.class)
class PingControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ping_est_public_et_repond_ok() throws Exception {
        mockMvc.perform(get("/api/ping"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    void une_route_inconnue_exige_une_authentification() throws Exception {
        mockMvc.perform(get("/api/route-inconnue"))
                .andExpect(status().is4xxClientError());
    }
}
