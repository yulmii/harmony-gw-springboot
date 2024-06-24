package com.harmony.gw.service;

import java.security.Key;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
   static final long EXPIRATIONTIME = 86400000; //1 일을 밀리초로 계산한 값
   static final String PREFIX = "Bearer";
   // 비밀 키 생성. 시연 용도로만 이용해야 함
   // 애플리케이션 구성에서 읽을 수 있음
   static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
   
   // 서명된 JWT 토큰 생성
   public String getToken(String userId) {
      String token = Jwts.builder()
            .setSubject(userId)
            .setExpiration(new Date(System.currentTimeMillis()
                  + EXPIRATIONTIME))
            .signWith(key)
            .compact();
      
      return token;
   }
   
   // 요청 권한 부여 헤더에서 토근을 가져와
   // 토큰을 확인하고 사용자 이름을 얻음
   public String getAuthUser(HttpServletRequest request) {
      String token = request.getHeader(HttpHeaders.AUTHORIZATION);
      
      if(token != null) {
         String user = Jwts.parserBuilder()
               .setSigningKey(key)
               .build()
               .parseClaimsJws(token.replace(PREFIX, ""))
               .getBody()
               .getSubject();
         
         if(user != null)
            return user;
      }
      return null;
   }
}
