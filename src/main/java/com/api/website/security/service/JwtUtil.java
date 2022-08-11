package com.api.website.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    private final String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String[] extractRoles(String token) {
        final String rolesString = extractAllClaims(token).get("roles").toString();
        String rolesArray[] = rolesString.substring(1, rolesString.length() - 1).split(",");
        // ** Remove the leading characters "{" || " {" as well as the trailing character "}"
        String resultArray[] = new String[rolesArray.length];
        for (int i = 0; i < rolesArray.length; i++) {
            resultArray[i] = rolesArray[i].startsWith("{")
                    ? rolesArray[i].substring("{authority=".length(), rolesArray[i].length() - 1)
                    : rolesArray[i].substring("{authority=".length()+1, rolesArray[i].length() - 1);
        }
        return resultArray;
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String createToken(Map<String, Object> claims, String subject, Integer seconds) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + secondsToMilli(seconds)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    private Integer secondsToMilli(Integer s) {
        return s * 1000;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // Add all roles to claims
        claims.put("roles", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername(), 300);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // Add all roles to claims
        claims.put("roles", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername(), 600);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

}
