package com.github.dehlynn.desafio.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JwtUtilsTest {

    private static final String JSON_CLAIMS_VALIDO = "{\"Name\":\"Um nome comum\",\"Seed\":\"3\",\"Role\":\"Admin\"}";
    private static final String JSON_CLAIMS_REQUISITOS_INVALIDO = "{\"Name\":\"Um nome n40 Comum\",\"Seed\":\"400\",\"Role\":\"Invalido\"}";
    private static final String JSON_CLAIMS_INVALIDO = "{\"Here\": \"Hoje\",\"What\": \"Ever\"}";
    private static final String JSON_TRES_CLAIMS_FORA_PADRAO_DETERMINADO = "{\"Name\":\"Um nome comum\",\"Cod\":\"3\",\"Role\":\"Admin\"}";
    private static final String JSON_INVALIDO = "abcda";

    private final JwtUtils jwtUtils = new JwtUtils();

    @Test
    void verificarJsonValidoEDecodificarPartesJwt() {
        String encodedString = Base64.getUrlEncoder().encodeToString(JSON_CLAIMS_VALIDO.getBytes());

        String resultado = jwtUtils.decodificarPartesJwt(encodedString);

        assertTrue(JwtUtils.verificarJsonValido(JSON_CLAIMS_VALIDO));
        assertEquals(JSON_CLAIMS_VALIDO, resultado);
    }

    @Test
    void verificaJsonENaoDecodificarPartesJwtPoisEInvalido() {
        String encodedString = Base64.getUrlEncoder().encodeToString(JSON_INVALIDO.getBytes());

        String resultado = jwtUtils.decodificarPartesJwt(encodedString);
        assertFalse(JwtUtils.verificarJsonValido(JSON_INVALIDO));
        assertEquals("{}", resultado);
    }

    @Test
    void deveVerificarQuantidadeClaimsValidoRetornandoTres() {
        int result = jwtUtils.verificarQuantidadeClaims(JSON_CLAIMS_VALIDO);
        assertEquals(3, result);
    }

    @Test
    void deveVerificarQuantidadeClaimsRetornandoFalsoParaTresClaimsEsperadosPoisTokenEInvalido() {
        int result = jwtUtils.verificarQuantidadeClaims(JSON_CLAIMS_INVALIDO);
        assertNotEquals(3, result);

    }

    @Test
    void possuiOsClaimsEsperados() {
        boolean resultado = jwtUtils.possuiClaimsEsperadas(JSON_CLAIMS_VALIDO, "Name", "Seed", "Role");

        assertTrue(resultado);
        assertEquals(true, resultado);
    }

    @Test
    void possuiTresClaimsMasNaoSaoOsEsperados() {
        boolean resultado = jwtUtils.possuiClaimsEsperadas(JSON_TRES_CLAIMS_FORA_PADRAO_DETERMINADO, "Name", "Seed", "Role");

        assertFalse(resultado);
        assertNotEquals(true, resultado);
    }

    @Test
    void deveraExtrairClaimCorretamente() {
        String resultado = jwtUtils.extrairClaim(JSON_CLAIMS_VALIDO, "Name");
        String resultado1 = jwtUtils.extrairClaim(JSON_CLAIMS_VALIDO, "Role");
        String resultado2 = jwtUtils.extrairClaim(JSON_CLAIMS_VALIDO, "Seed");
        assertEquals("Um nome comum", resultado);
        assertEquals("Admin", resultado1);
        assertEquals("3", resultado2);

    }

}





