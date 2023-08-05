package com.girishms.springbootdemo.security;

import com.girishms.springbootdemo.Entities.User;
import com.girishms.springbootdemo.exceptions.CustomException;
import com.girishms.springbootdemo.exceptions.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

public class JwtTokenUtilImpl implements JwtTokenUtil<User> {
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    @Value("${token.secret}")
    private String secret;

    @Override
    public String generateToken(User user) {
        Instant now = Instant.now();
        Instant expirationInstance = now.plusMillis(EXPIRATION_TIME);
        Key key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .signWith(key)
                .setExpiration(Date.from(expirationInstance))
                .setIssuedAt(Date.from(now))
                .compact();
    }

    @Override
    public Jws<Claims> validateToken(String token) {
        try{
            Key key = Keys.hmacShaKeyFor(secret.getBytes());
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        }catch (Exception exception) {
            return null;
        }
    }

    @Override
    public User getUserFromAuthorizationHeader(HttpServletRequest request) {
        try{
            String token = request.getHeader("Authorization").split("")[1];
            Jws<Claims> claimsJws = validateToken(token);

            Long userId = Long.valueOf(claimsJws.getBody().getSubject());
            return null;
        }catch (Exception exception) {
            throw  new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }
}
