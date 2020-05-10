package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Post;
import by.bsuir.tattoo4u.entity.User;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PostResponseDto {
    private Long id;
    private String description;
    private Long authorId;
    private String authorName;
    private String photoUrl;
    private List<String> tags;
    private Long likesNumber;
    private boolean liked;

    public void fromPost(Post post, User user) {
        id = post.getId();
        description = post.getDescription();
        authorId = post.getAuthor().getId();
        authorName = post.getAuthor().getUsername();
        photoUrl = post.getPhoto().getUrl();
        tags = post.getTags();

        Set<User> likes = post.getLikes();
        likesNumber = (long) likes.size();

        if (user != null) {
            liked = likes.contains(user);
        } else {
            liked = false;
        }
    }
}
