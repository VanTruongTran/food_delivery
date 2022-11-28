package com.project.food_delivery.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenHelper {
    //thời gian hết hạn (milisec)
    private long expiredTime = 8 * 60 * 60 * 1000;

    //chuỗi BASE64 (>= 256 bits)
    private String stringKey = "U3ByaW5nIEJvb3QgaXMgYW4gb3BlbiBzb3VyY2UgSmF2YS1iYXNlZCBmcmFtZXdvcmsgdXNlZCB0byBjcmVhdGUgYSBtaWNybyBTZXJ2aWNl";

    //phương thức mã hóa dữ liệu thành chuỗi token
    public String generateToken(String data) {
        Date createdDate = new Date();
        Date expiredDate = new Date(createdDate.getTime() + expiredTime);
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(stringKey));

        return Jwts.builder()
                .setSubject(data)//lưu trữ dữ liệu vào trong token (kiểu string)
                .setIssuedAt(createdDate)//thời gian tạo token
                .setExpiration(expiredDate)//thời gian token hết hạn
                .signWith(secretKey, SignatureAlgorithm.HS256)//thuật toán mã hóa và secret key
                .compact();//trả ra một chuỗi token đã được mã hóa
    }

    //phương thức giải mã chuỗi token
    public String decodeToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(stringKey));
        return Jwts.parserBuilder()
                .setSigningKey(secretKey).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    //phương thức kiểm tra chuỗi token có phải do hệ thống tạo ra hay không
    public boolean validateToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(stringKey));
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey).build()
                    .parseClaimsJws(token);//decode token
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }
        return false;
    }
}
