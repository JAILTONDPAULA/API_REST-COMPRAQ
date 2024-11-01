package br.com.traumfabrik.compraoq.settings.security;

import br.com.traumfabrik.compraoq.entities.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserEntity usuario) {
        try {
            return JWT.create()
                      .withIssuer("API COMPRAOQ")
                      .withSubject(usuario.getUsername())
                      .withExpiresAt(getTime())
                      .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token");
        }

    }

    public String decodeToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("API COMPRAOQ")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new BadCredentialsException("Token inválido ou expirado");
        }
    }

    private Instant getTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
