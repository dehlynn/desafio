package com.github.dehlynn.desafio.util;

import com.github.dehlynn.desafio.model.Role;

import java.util.regex.Pattern;

import static jdk.dynalink.linker.support.Guards.isNotNull;

public class ClaimsUtils {
    public boolean nameAtendeRequisitos(String name) {
        boolean naoNullOuVazio = (name != null && !name.isEmpty());
        boolean atendePadraoDeTamanho = naoNullOuVazio && name.length() <= 256;
        boolean naoContemNumero = naoNullOuVazio && !Pattern.compile("[0-9]").matcher(name).find();

        return naoNullOuVazio && atendePadraoDeTamanho && naoContemNumero;
    }

    public boolean roleAtendeRequisitos(String roleName) {
        for (Role role : Role.values()) {
            if (role.getNomeRoles().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    public boolean seedAtendeRequisitos(String seed) {
        int seedValue = Integer.parseInt(seed);
        if (seedValue <= 1) {
            return false;
        }
        for (int i = 2; i <= seedValue / 2; i++) {
            if (seedValue % i == 0) {
                return false;
            }
        }
        return true;
    }


}
