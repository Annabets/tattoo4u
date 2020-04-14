package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.entity.Token;
import by.bsuir.tattoo4u.repository.TokenRepository;
import by.bsuir.tattoo4u.security.jwt.JwtTokenProvider;
import by.bsuir.tattoo4u.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository, JwtTokenProvider jwtTokenProvider) {
        this.tokenRepository = tokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
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
        return jwtTokenProvider.getUsername(token);
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
}
