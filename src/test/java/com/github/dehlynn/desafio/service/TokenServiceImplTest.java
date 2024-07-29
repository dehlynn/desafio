package com.github.dehlynn.desafio.service;

import com.github.dehlynn.desafio.exception.TokenInvalidoException;
import com.github.dehlynn.desafio.service.impl.TokenServiceImpl;
import com.github.dehlynn.desafio.util.ClaimsUtils;
import com.github.dehlynn.desafio.util.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private ClaimsUtils claimsUtils;

    private static final String TOKEN_JWT_INVALIDO = "VCJ9.eyJzdWIiOiIxMjM0NTY3ODkwI";
    private static final String TOKEN_VALIDO = "eyJhbGciOiJIUzI1NiJ9.eyJOYW1lIjoiVW0gbm9tZSBjb211bSIsIlNlZWQiOiIzIiwiUm9sZSI6IkFkbWluIn0.0MVFy54sbbBXBpyGAicZECBvRBVryvx_xdQP04KZ7xQ";
    private static final String TOKEN_CLAIMS_INVALIDO = "eyJhbGciOiJIUzI1NiJ9.eyJOYW1lIjoiVW0gbm9tZSBuNDAgQ29tdW0gIiwiU2VlZCI6IjQwMCIsIlJvbGUiOiJJbnZhbGlkbyJ9.YPDEbpiGKcJhcHWRn3eK8-C1btibRos_e7_kPaorig0";

    private static final String JSON_CLAIMS_VALIDO = "{\"Name\":\"Um nome comum\",\"Seed\":\"3\",\"Role\":\"Admin\"}";
    private static final String JSON_CLAIMS_INVALIDO = "{\"Name\":\"Um nome n40 Comum\",\"Seed\":\"400\",\"Role\":\"Invalido\"}";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        setupMocks();
    }

    void setupMocks() {
        when(jwtUtils.decodificarPartesJwt(anyString())).thenAnswer(invocation -> {
            String tokenPart = invocation.getArgument(0);
            if (tokenPart.equals(TOKEN_VALIDO.split("\\.")[1])) {
                return JSON_CLAIMS_VALIDO;
            } else if (tokenPart.equals(TOKEN_CLAIMS_INVALIDO.split("\\.")[1])) {
                return JSON_CLAIMS_INVALIDO;
            } else {
                return "";
            }
        });

    }

    @Test
    void deveRetornarExceptionPoisJwtEstaNulo() {
        assertThrows(TokenInvalidoException.class, () -> {
            tokenService.validar(null);
        });
    }

    @Test
    void deveRetornarExceptionPoisJwtEstaVazio() {
        assertThrows(TokenInvalidoException.class, () -> {
            tokenService.validar("");
        });
    }

    @Test
    void deveRetornarFalsoParaJwtInvalido() {

        boolean resultado = tokenService.validar(TOKEN_JWT_INVALIDO);

        assertFalse(resultado);

    }

    @Test
    void deveRetornarVerdadeiroQuandoTokenCumpreRequisitos() {

        boolean result = tokenService.validar(TOKEN_VALIDO);

        assertTrue(result);

    }

    @Test
    void deveRetornarFalsoQuandoTokenNaoCumpreRequisitos() {

        boolean result = tokenService.validar(TOKEN_CLAIMS_INVALIDO);

        assertFalse(result);

    }

}