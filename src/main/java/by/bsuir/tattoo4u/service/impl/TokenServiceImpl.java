package by.bsuir.tattoo4u.service.impl;

import by.bsuir.tattoo4u.entity.Token;
import by.bsuir.tattoo4u.repository.TokenRepository;
import by.bsuir.tattoo4u.service.TokenService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.token.secret}")
    private String secretWord;

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token add(String token) {
        return tokenRepository.save(new Token(token));
    }

    @Override
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretWord).parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public void delete(String string) {
        if (tokenRepository.existsByToken(string)) {
            Token token = tokenRepository.getByToken(string);
            tokenRepository.deleteById(token.getId());
        }
    }

    @Override
    public boolean exists(String string) {
        return tokenRepository.existsByToken(string);
    }
}
