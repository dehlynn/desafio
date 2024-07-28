package com.github.dehlynn.desafio.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.Base64;

public class JwtUtils {

    public String decodificarPartesJwt(String partesJwt) {
        byte[] bytes = Base64.getUrlDecoder().decode(partesJwt);
        String stringDecodificada = new String(bytes);
        return verificarJsonValido(stringDecodificada) ? stringDecodificada : "{}";
    }


    public static boolean verificarJsonValido(String json) {
        try {
            new ObjectMapper().readTree(json);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    public int verificarQuantidadeClaims(String claimsJson) {
        return verificarJsonValido(claimsJson) ? new JSONObject(claimsJson).length() : 0;
    }

    public String extrairClaim(String jsonClaims, String claimProcurada) {
        return new JSONObject(jsonClaims).getString(claimProcurada);
    }

    public boolean possuiClaimsEsperadas(String jsonClaims, String... claimsProcuradas) {
        JSONObject jsonObject = new JSONObject(jsonClaims);
        for (String claim : claimsProcuradas) {
            if (!jsonObject.has(claim)) {
                return false;
            }
        }
        return true;
    }
}
