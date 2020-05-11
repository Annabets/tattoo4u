package by.bsuir.tattoo4u.security.jwt;

import by.bsuir.tattoo4u.entity.Role;
import by.bsuir.tattoo4u.service.TokenService;
import by.bsuir.tattoo4u.service.impl.TokenServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secretWord;

    @Value("${jwt.token.expired}")
    private Long validityInMillisecond;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private TokenService tokenService;

    protected void init() {
        secretWord = Base64.getEncoder().encodeToString(secretWord.getBytes());
    }

    public String createToken(String username, List<Role> roleList) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roleList);

        Date nowDate = new Date();
        Date endOfValidityDate = new Date(nowDate.getTime() + validityInMillisecond);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(endOfValidityDate)
                .signWith(SignatureAlgorithm.HS256, secretWord)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretWord).parseClaimsJws(token).getBody().getSubject();
    }


    public Authentication getAuthentication(String token) throws UsernameNotFoundException {

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        String result = null;

        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && (bearerToken.startsWith("Bearer_") || bearerToken.startsWith("Bearer "))) {
            result = bearerToken.substring(7);
        }

        return result;
    }

    public boolean isValidToken(String token) throws JwtAuthenticationException {
        try {
            boolean result = true;

            Jws<Claims> claims = Jwts.parser().setSigningKey(secretWord).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                result = false;
                tokenService.delete(token);
            }

            return result;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }
}
