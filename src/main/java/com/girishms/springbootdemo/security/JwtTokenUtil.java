package com.girishms.springbootdemo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;

public interface JwtTokenUtil<U> {
    String generateToken(U user);
    Jws<Claims> validateToken(String token);
    U getUserFromAuthorizationHeader(HttpServletRequest request);
}
