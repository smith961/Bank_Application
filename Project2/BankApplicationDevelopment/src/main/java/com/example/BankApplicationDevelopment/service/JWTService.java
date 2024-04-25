package com.example.BankApplicationDevelopment.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

     /*

    csrf -> disable (JWT json web token) JWT Service
     Security filter chain
    Security Configuration Class
     */

    private static final String SECRET_KEY = "ZSAWERTYUIO1234567890NGFDSWERTYUJIKOLPSDFREWQ2345678UJHN";

    private Key getSigninKey(){
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes); //converted to a secret key
    }

    public String createToken(UserDetails userDetails){
        return createFreshToken(new HashMap<>(), userDetails);
    }

    private String createFreshToken(Map<String, Object> mapOfClaims, UserDetails userDetails){
        return Jwts.builder()
                .addClaims(mapOfClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .setIssuer("Banking App 1.0")
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token){//helps to bring out all the claims into an object called claim
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)//jwts builder.
                .getBody();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);

    }

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);

    }

    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    public Date extractDateIssued(String token){
        return extractClaims(token, Claims::getIssuedAt);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    private boolean isTokenGeneratedFromServer(String token){
        return extractClaims(token, Claims::getIssuer).equals("Banking App 1.0");

    }




    public boolean isTokenValid (String token, UserDetails userDetails){
        String username = extractClaims(token, Claims::getSubject);
        return username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token);

    }



//    isTokenValid -> isTokenExpired -> userIsOwner

}
