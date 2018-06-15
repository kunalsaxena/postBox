package com.mailclient.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/** Created by kunal on 17/3/17. */
public class JWTTokenUtil {

  public static final String createToken(String username, String password) {
    Key key = MacProvider.generateKey();

    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put("username", username);
    claims.put("password", password);

    String compactJws =
        Jwts.builder()
            .setSubject("Joe")
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, PBConstants.JWT_TOKEN_KEY)
            .compact();
    return compactJws;
  }

  public static final Map<String, Object> parseToken(String token) {
    Key key = MacProvider.generateKey();
    Claims claims =
        Jwts.parser().setSigningKey(PBConstants.JWT_TOKEN_KEY).parseClaimsJws(token).getBody();
    return (Map<String, Object>) claims;
  }
}
