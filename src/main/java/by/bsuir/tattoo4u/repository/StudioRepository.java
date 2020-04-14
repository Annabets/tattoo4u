package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudioRepository extends JpaRepository<Studio, Long> {
    Page<Studio> findAll(Pageable pageable);

    Page<Studio> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    Studio getByName(String name);

    Studio getById(Long id);

    @Modifying
    @Query("UPDATE Studio s SET s.masters = :masters WHERE s.owner = :userId")
    void update(@Param("masters")List<User> masters, @Param("userId")Long userId);

    //void merge(Studio studio);
}
