package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.entity.Token;
import by.bsuir.tattoo4u.entity.User;
import by.bsuir.tattoo4u.repository.TokenRepository;
import by.bsuir.tattoo4u.service.TokenService;
import by.bsuir.tattoo4u.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.token.secret}")
    private String secretWord;

    private final TokenRepository tokenRepository;

    private final UserService userService;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository, UserService userService) {
        this.tokenRepository = tokenRepository;
        this.userService = userService;
    }

    @Override
    public Token add(String token) {
        return tokenRepository.save(new Token(token));
    }

    @Override
    public String clearBearerToken(String bearerToken) {
        String token = null;
        if (bearerToken != null && (bearerToken.startsWith("Bearer_") || bearerToken.startsWith("Bearer "))) {
            token = bearerToken.substring(7);
        }

        return token;
    }

    @Override
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretWord).parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public void delete(String string) {
        if (tokenRepository.existsByToken(string)) {
            tokenRepository.deleteByToken(string);
        }
    }

    @Override
    public boolean exists(String string) {
        return tokenRepository.existsByToken(string);
    }

    @Override
    public User getUser(String bearerToken) {
        User user = null;

        String token = clearBearerToken(bearerToken);

        if (token != null) {
            String username = getUsername(token);

            user = userService.getByUsername(username);
        }

        return user;
    }
}
