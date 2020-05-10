package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
