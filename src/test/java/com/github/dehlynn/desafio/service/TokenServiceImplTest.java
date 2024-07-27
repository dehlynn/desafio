package com.github.dehlynn.desafio.service;

import com.github.dehlynn.desafio.service.impl.TokenServiceImpl;
import com.github.dehlynn.desafio.util.ClaimsUtils;
import com.github.dehlynn.desafio.util.JwtUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private ClaimsUtils claimsUtils;

}