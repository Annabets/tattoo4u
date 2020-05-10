package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Order;
import by.bsuir.tattoo4u.entity.Studio;
import by.bsuir.tattoo4u.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByAuthor(User user);

    List<Order> findAllByStudio(Studio studio);

    List<Order> findAllByAuthorAndStudioId(User user, Long studioId);

    Order getById(Long id);
}
