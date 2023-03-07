package com.example.demogrpc.server.Security.interceptor;

import com.example.demogrpc.server.Constants;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Component
@NoArgsConstructor
public class ValidateToken {
    public static Jws<Claims> parseJwt(String jwtString) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(Constants.JWT_SIGNING_KEY),
                SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> jwt = Jwts.parser()
                .setSigningKey(hmacKey)
                .parseClaimsJws(jwtString);

        return jwt;
    }

    public Jws<Claims> getValidToken(String jwtToken){
        if (Strings.isNullOrEmpty(jwtToken)) {
            return null;
        }else if (!jwtToken.startsWith("Bearer ")) {
            return null;
        }else {
            String token = jwtToken.substring("Bearer ".length());

            return parseJwt(token);

        }
    }
}
