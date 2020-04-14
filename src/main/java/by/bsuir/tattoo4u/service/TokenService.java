package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.entity.Token;

public interface TokenService {

    String clearBearerToken(String bearerToken);

    String getUsername(String token);

    Token add(String token);

    void delete(String string);

    boolean exists(String string);
}
