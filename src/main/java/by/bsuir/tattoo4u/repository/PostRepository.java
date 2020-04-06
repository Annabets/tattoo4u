package by.bsuir.tattoo4u.repository;

import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    Iterable<Post> findByAuthor(User user);

    //Iterable<Post> findByTagsInContainingIgnoreCase(List<String> tags);
    //Iterable<Post> findByTagsInContainingIgnoreCase(String tags);

    //Iterable<Post> findByTagsContaining(List<String> String);

//    @Query(
//            "SELECT s FROM Student s JOIN s.skillTags t WHERE t.name = LOWER(:tagName) AND t.value > :tagValue")
//    List<Student> retrieveByNameFilterByMinimumSkillTag(
//            @Param("tagName") String tagName, @Param("tagValue") int tagValue);
//

    //Iterable<Post> retrieveByTags(@Param("") List<String> tags);
}