package br.com.docesdalu.application.controller;

import br.com.docesdalu.application.config.security.JWTGenerator;
import br.com.docesdalu.application.dto.input.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    private final JWTGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthController(JWTGenerator jwtGenerator, AuthenticationManager authenticationManager) {
        this.jwtGenerator = jwtGenerator;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String token(@RequestBody LoginRequest userLogin) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getNome(), userLogin.getSenha()));
        return jwtGenerator.generateToken(authentication);
    }

}
