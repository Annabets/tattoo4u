package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Master;
import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterRepository extends JpaRepository<Master, Long> {

    Master getByUser(User user);
    Page<Master> findAll(Pageable pageable);
    Page<Master> findAllByUser_UsernameContainingIgnoreCase(String user_username, Pageable pageable);

    List<Master> findAllByJob(Studio job);
}
