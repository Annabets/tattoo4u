package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudioRepository extends JpaRepository<Studio, Long> {
    Page<Studio> findAll(Pageable pageable);

    Page<Studio> findAllByConfirmationIsTrue(Pageable pageable);

    //Page<Studio> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Studio> findAllByNameContainingIgnoreCaseAndConfirmationIsTrue(String name, Pageable pageable);

    List<Studio> findAllByConfirmationIsFalse();

    Studio getById(Long id);

    void deleteById(Long id);

    Studio getByOwner(User owner);
}
