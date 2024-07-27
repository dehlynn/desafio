package com.github.dehlynn.desafio.service.impl;

import com.github.dehlynn.desafio.service.TokenService;
import com.github.dehlynn.desafio.util.ClaimsUtils;
import com.github.dehlynn.desafio.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private static final int MAXIMO_CLAIMS = 3;
    private static final int PARTES_JWT = 3;
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    private final JwtUtils jwtUtils = new JwtUtils();
    private final ClaimsUtils claimsUtils = new ClaimsUtils();

    @Override
    public Boolean validar(String token) {

        String[] partesJwt = token.split("\\.");
        if (partesJwt.length != PARTES_JWT) {
            LOGGER.info("Token não possui um formato válido");
            return false;
        }

        String jsonClaims = jwtUtils.decodificarPartesJwt(partesJwt[1]);

        if (jwtUtils.verificarQuantidadeClaims(jsonClaims) != MAXIMO_CLAIMS) {
            LOGGER.info("Token não possui quantidade de claim determinada");
            return false;
        }

        String name = jwtUtils.extrairClaim(jsonClaims, "Name");
        String seed = jwtUtils.extrairClaim(jsonClaims, "Seed");
        String role = jwtUtils.extrairClaim(jsonClaims, "Role");

        LOGGER.info("Informações do token - Name: {}, Seed: {}, Role: {}", name, seed, role);

        return claimsUtils.nameAtendeRequisitos(name) &&
                claimsUtils.roleAtendeRequisitos(role) &&
                claimsUtils.seedAtendeRequisitos(seed);

    }


}
