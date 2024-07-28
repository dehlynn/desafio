package com.github.dehlynn.desafio.util;

import com.github.dehlynn.desafio.model.Role;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ClaimsUtilsTest {
    private final ClaimsUtils claimsUtils = new ClaimsUtils();

    String nomeValido = RandomStringUtils.randomAlphabetic(0, 256);
    String nomeComCaracterValido = RandomStringUtils.randomAlphanumeric(0, 256);
    String nomePossiCaracteresMaiorQuePermitido = RandomStringUtils.randomAlphabetic(257);
    String roleValido = Role.ADMIN.getNomeRoles();
    String roleInvalido = RandomStringUtils.random(50);

    String seedValido = "5";
    String seedInvalido = "4";


    @Test
    void verificaNomeAtendeTodosRequisitos() {
        assertTrue(claimsUtils.nameAtendeRequisitos(nomeValido));
    }

    @Test
    void verificaNomeNuloOuVazio() {
        assertFalse(claimsUtils.nameAtendeRequisitos(null));
        assertFalse(claimsUtils.nameAtendeRequisitos(""));
    }

    @Test
    void verificaNomeNaoAtendeRequisitoTamanho() {
        assertFalse(claimsUtils.nameAtendeRequisitos(nomePossiCaracteresMaiorQuePermitido));
    }

    @Test
    void verificaNomeNaoAtendeRequisitosCaracteres() {
        assertFalse(claimsUtils.nameAtendeRequisitos(nomeComCaracterValido));
    }

    @Test
    void verificaRoleAtendeRequisitos() {

        assertTrue(claimsUtils.roleAtendeRequisitos(roleValido));
    }

    @Test
    void verificaRoleNaoAtendeRequisitos() {
        assertFalse(claimsUtils.roleAtendeRequisitos(roleInvalido));
    }

    @Test
    void verificaSeedAtendeRequisitos() {

        assertTrue(claimsUtils.seedAtendeRequisitos(seedValido));
    }

    @Test
    void verificaSeedNaoAtendeRequisitos() {
        assertFalse(claimsUtils.seedAtendeRequisitos(seedInvalido));
    }


}
