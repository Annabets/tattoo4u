package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token getByToken(String token);

    boolean existsByToken(String token);

    @Transactional
    void deleteByToken(String token);
}
