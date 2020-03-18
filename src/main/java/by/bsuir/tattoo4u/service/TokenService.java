package by.bsuir.tattoo4u.service;

import by.bsuir.tattoo4u.entity.Token;

public interface TokenService {

    Token add(String token);

    void delete(String string);

    boolean exists(String string);
}
