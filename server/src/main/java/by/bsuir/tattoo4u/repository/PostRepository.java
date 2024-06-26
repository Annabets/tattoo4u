package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    Page<Post> findByAuthor(User user, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT post.* FROM post INNER JOIN post_tags ON post.id=post_tags.post_id WHERE tags IN (:tags)" +
                    " GROUP BY post.id ORDER BY COUNT(post_tags.tags) DESC")
    Page<Post> findByTags(@Param("tags") List<String> tags, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT post.* FROM post INNER JOIN post_likes ON post.id=post_likes.post_id GROUP BY post.id" +
                    " ORDER BY COUNT(post_likes.user_id) DESC")
    Page<Post> findTrends(Pageable pageable);

    Page<Post> findAll(Pageable pageable);
}