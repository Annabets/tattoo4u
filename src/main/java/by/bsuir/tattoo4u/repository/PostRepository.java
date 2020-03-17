package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
