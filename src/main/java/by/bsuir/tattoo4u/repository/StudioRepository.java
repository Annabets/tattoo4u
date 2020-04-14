package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Studio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<Studio, Long> {
    Page<Studio> findAll(Pageable pageable);

    Page<Studio> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    Studio getById(Long id);

    void deleteById(Long id);
}
