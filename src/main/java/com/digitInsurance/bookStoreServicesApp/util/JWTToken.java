package com.digitInsurance.bookStoreServicesApp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.UUID;

public class JWTToken {

    private static final String SECRET_KEY = "There's_No_Secret";
    private static final String ISSUER = "Team3";
    private static final long EXPIRATION_TIME = 30000000L; // 8 hours 20 minutes
    private static final long NOT_BEFORE_TIME = 1000L; // 1 second

    public static String getToken(String role) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);
        Date notBefore = new Date(now.getTime() + NOT_BEFORE_TIME);

        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim("role", role)
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(notBefore)
                .sign(algorithm);

    }


}
