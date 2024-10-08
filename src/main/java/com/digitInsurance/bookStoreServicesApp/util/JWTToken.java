package com.digitInsurance.bookStoreServicesApp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;

import java.util.Date;
import java.util.UUID;

public class JWTToken {

    private static final String SECRET_KEY = "There's_No_Secret";
    private static final String ISSUER = "Team3";
    private static final long EXPIRATION_TIME = 30000000L; // 8 hours 20 minutes
    private static final long NOT_BEFORE_TIME = 1000L; // 1 second

    public static String getToken(String role, Long id) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);
        Date notBefore = new Date(now.getTime() + NOT_BEFORE_TIME);

        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim("role", role)
                .withClaim("id",id)
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(notBefore)
                .sign(algorithm);

    }

    public static String getRoleFromToken(String token) throws TokenNotValidException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token.replace("Bearer ", ""));
            return decodedJWT.getClaim("role").asString();
        }
        catch (RuntimeException e){
            throw new TokenNotValidException("Ohh! Your token is wrong");
        }
    }


    public static Long getUserIdFromToken(String token) throws TokenNotValidException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token.replace("Bearer ", ""));
            return decodedJWT.getClaim("id").asLong();
        } catch (JWTVerificationException e) {
            throw new TokenNotValidException("Invalid token");
        }
    }
}
