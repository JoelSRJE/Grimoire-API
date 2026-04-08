package com.project.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;

@Service
public class JWTService {

    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;

    public JWTService(@Value("${JWT_SECRET:}") String secret) {

        if (secret == null || secret.isBlank()) {
            secret = generateJWTSecret();
        }

        this.algorithm = Algorithm.HMAC256(secret);
        this.jwtVerifier = JWT.require(this.algorithm)
                .withIssuer("backend")
                .build();
    }

    private String generateJWTSecret() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[64];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public String generateToken(UUID userId) {
        return JWT.create()
                .withIssuer("backend")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(20, ChronoUnit.MINUTES))
                .withSubject(userId.toString())
                .withClaim("Authorized", true)
                .sign(algorithm);
    }

    public UUID validateToken(String token)  {
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        return UUID.fromString(decodedJWT.getSubject());
    }

}
