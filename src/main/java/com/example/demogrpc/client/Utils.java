package com.example.demogrpc.client;

import com.example.demogrpc.server.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
@NoArgsConstructor
public class Utils {
    public String generateToken() {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(Constants.JWT_SIGNING_KEY),
                SignatureAlgorithm.HS256.getJcaName());

        Instant now = Instant.now();

        return Jwts.builder()
                .claim("name", "Nhung")
                .claim("email", "nhungtth10@tcbs.com.vn")
                .setSubject("Nhung")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, hmacKey)
                .compact();
    }
}
