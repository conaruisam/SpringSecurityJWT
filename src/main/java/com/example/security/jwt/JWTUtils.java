package com.example.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Component
public class JWTUtils {

    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    // Generar token de acceso
    public String generateAccessToken(String username) {
        // Codifica el tokenn.
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis())) // El inicio
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration))) // La expiración
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256) //Firmar con el algoritmo HS256
                .compact();
    }

    // Validar el token de acceso
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder() //Descifra el token
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception ex) {
            log.error("Token invalido, error ".concat(ex.getMessage()));
            return false;
        }
    }

    // Obtener el username del token
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    // Obtener un solo Claim
    public <T> T getClaim(String token, Function<Claims, T> claimsFunction){
        Claims claims = extractAllKlaims(token);
        return claimsFunction.apply(claims);
    }
    // Obtener todos los claims del token
    public Claims extractAllKlaims(String token) {
        return Jwts.parserBuilder() //Descifra el token
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Obtener firma del token
    public Key getSignatureKey() {
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
