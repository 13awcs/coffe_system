package com.coffe_management_system.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.coffe_management_system.auth.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenUtil {

    @Value("${bactv.jwtsecret}")
    private String bactvJwtSecret;

    @Value("${bactv.jwtissuer}")
    private String bactvJwtIssuer;


    @PostConstruct
    void print(){
        System.err.println(bactvJwtSecret+bactvJwtIssuer);
    }
    //todo: move this to application.properties to make it dynamic in multiple env
//    private final String jwtSecret = "CctlD5JL16m8wLTgsFNhzqjQP";
//    private final String jwtIssuer = "coder4.life";

    public String generateAccessToken(User user) {
        Algorithm algorithm = Algorithm.HMAC512(bactvJwtSecret.getBytes());

        List<String> authorities = new ArrayList<>();
        authorities.add(user.getRole());


        return JWT.create()
                .withSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .withIssuer(bactvJwtIssuer)
                .withClaim("roles", authorities)
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 100000)) //todo: how long is this, should declare constants with clear name
                .sign(algorithm);
    }

    public String generateRefreshToken(User user) {
        Algorithm algorithm = Algorithm.HMAC512(bactvJwtSecret.getBytes());

        return JWT.create()
                .withSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .withIssuer(bactvJwtIssuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .sign(algorithm);
    }

    public boolean validate(String token) {
        try {
            Long expiresAt = JWT.decode(token).getExpiresAt().getTime();
            Calendar cal = Calendar.getInstance();
            if (expiresAt >= cal.getTime().getTime()) {
                return true;
            }
        } catch (IllegalArgumentException e) { //todo: use logger
            System.out.println(String.format("JWT is invalid - {%s}", e.getMessage()));
        }

        return false;
    }

    public String getUserName(String token) {
        String tokenAfter = token.replace("Bearer ", "");
        String subject = JWT.decode(tokenAfter).getSubject();

        return subject.split(",")[1];
    }

    public Long getUserId(String token) {
        String tokenAfter = token.replace("Bearer ", "");
        String subject = JWT.decode(tokenAfter).getSubject();
        return Long.valueOf(subject.split(",")[0]);
    }

}
