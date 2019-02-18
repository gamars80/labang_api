package com.mobin.config;

import static com.mobin.common.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.mobin.common.Constants.SIGNING_KEY;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mobin.model.SellerVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SuppressWarnings("serial")
@Component
public class JwtTokenUtil implements Serializable {

//    public String getUsernameFromToken(String token) {
//    	Claims claims =  getClaimFromToken(token);
//    	return claims.get("username").toString();
//       // return getClaimFromToken(token, Claims::getSubject);
//    }
//    
//    public int getUserIdFromToken(String token) {
//    	Claims claims =  getClaimFromToken(token);
//    	return Integer.parseInt(claims.get("id").toString());
//    }
	
	public String getSellerIdFromToken(String token) {
    	Claims claims =  getClaimFromToken(token);
    	return claims.get("sellerid").toString();
    }

	
    public Long getSidFromToken(String token) {
    	Claims claims =  getClaimFromToken(token);
    	return Long.parseLong(claims.get("sid").toString());
    }
    
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Claims getClaimFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims;
    }
    
    
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(SellerVO sellerVO) {
        return doGenerateToken(sellerVO,"seller");
    }

    private String doGenerateToken(SellerVO sellerVO, String subject) {

        Claims claims = Jwts.claims();
        claims.put("sellerid", sellerVO.getId());
        claims.put("sid", sellerVO.getUid());
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_SELLER")));
       

        return Jwts.builder()
        		 .setClaims(claims)
        		 .setIssuedAt(new Date(System.currentTimeMillis()))
        	        .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
        	        .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
        	        .compact();
       
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String sellerId = getSellerIdFromToken(token);
        return (
        		sellerId.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }

}
