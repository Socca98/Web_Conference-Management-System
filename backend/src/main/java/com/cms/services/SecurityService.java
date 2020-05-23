package com.cms.services;

import com.cms.dto.token.TokenInformation;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

@Service
public class SecurityService {
    public static boolean INITIALIZATION = false;
    private static String SECRET_KEY = "UwanByYRQ5s17ifzeZW-gjik1EwNTaxUX72r9fX7-wVNlE1k9AexawcXRVow86xlxLqdaVlzCIDVQigftVtWxlmw8umbtf6FD5XYktYb2hruDr_Xr_gDbI5jvLXK0HOLAEnkwSxzTO0Zauw0CUNwikvNMTF1VdZogbmHLyh3Rxz4bqBsii3RTwzOwaEF_u2jTJI6sSbooDD4jGVG4zcb9beag0yQx27Y1DPlK55wdY5lw-xAtyXUpCwVmoM4XCwIGr6pawsLGpMb4sCRd3RexwvCvkXls2u3J_wMd1cnLBkGmzFuqilDxXX-GlWEVJPk9RkdUog5FoMTkKrIZb-DTA";

    @Value("${predefined.admin.username}")
    private String adminUsername;

    public String getUsernameFromContext() {
        return Optional.of(getToken())
                .map(SecurityService::getUsername)
                .orElse(null);
    }

    private String getToken() {
        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                        .getRequest();
        return Optional.ofNullable(request.getHeader("Authorization"))
                .map(s -> s.replace("Bearer ", ""))
                .orElse(null);
    }

    public static String getUsername(String token) {
        return (String) decodeJWT(token).getHeader().get("username");
    }

    public static String createJWT(String id, Map<String, Object> headers, int ttlSeconds) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Calendar calendar = Calendar.getInstance();
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(calendar.getTime())
                .setHeader(headers)
                .setIssuer("IssBackend")
                .signWith(signatureAlgorithm, signingKey);
        calendar.add(Calendar.SECOND, ttlSeconds);
        builder.setExpiration(calendar.getTime());
        return builder.compact();
    }

    public static Jws<Claims> decodeJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt);
    }

    public boolean isPrehandle() {
        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                        .getRequest();
        String header = request.getHeader("access-control-request-headers");
        return Objects.nonNull(header);
    }

    public boolean checkToken() {
        String token = getToken();
        if (Objects.isNull(token)) {
            return false;
        }
        Jws<Claims> claims = SecurityService.decodeJWT(token);
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwsHeader header = claims.getHeader();
        return header.get("scope").equals("auth") && claims.getBody().getExpiration().after(now);
    }

    public TokenInformation getTokenInformation() {
        String token = getToken();
        Jws<Claims> claims = SecurityService.decodeJWT(token);
        Date expiration = claims.getBody().getExpiration();
        String username = (String) claims.getHeader().get("username");
        return new TokenInformation()
                .timeout((expiration.getTime() - new Date(System.currentTimeMillis()).getTime()) / 1000)
                .username(username);
    }

    public boolean isAdmin() {
        return getUsernameFromContext().equals(adminUsername) || INITIALIZATION;
    }
}
