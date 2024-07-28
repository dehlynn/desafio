package com.github.dehlynn.desafio.controller;

import com.github.dehlynn.desafio.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class JwtController {

    private final TokenService tokenService;

    @Operation(summary = "Valida um token recebido como parâmetro se é válido ou não.")
    @PostMapping("/jwt/validar")
    @ResponseStatus(code = HttpStatus.OK)
    public Boolean validarJWT(@RequestParam String token) {

        return tokenService.validar(token);
    }

}
