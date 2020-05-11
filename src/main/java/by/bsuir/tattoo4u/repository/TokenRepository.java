package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token getByToken(String token);

    boolean existsByToken(String token);

    void deleteByToken(String token);
}
