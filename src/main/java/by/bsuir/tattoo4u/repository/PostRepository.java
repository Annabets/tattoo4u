package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    Iterable<Post> findByAuthor(User user);

    @Query(nativeQuery = true,
            value = "SELECT * FROM post WHERE id IN (SELECT post_id FROM post_tags WHERE tags IN (:tags))" +
                    " ORDER BY id DESC")
    Iterable<Post> findByTags(@Param("tags") List<String> tags);

    @Query(nativeQuery = true,
            value = "SELECT post.* FROM post INNER JOIN post_likes ON post.id=post_likes.post_id GROUP BY post.id" +
                    " ORDER BY COUNT(post_likes.user_id) DESC")
    Iterable<Post> findTrends();
}