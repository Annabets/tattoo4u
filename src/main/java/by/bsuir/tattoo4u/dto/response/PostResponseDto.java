package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponseDto {
    private Long id;
    private String description;
    private String authorName;
    private String photoUrl;
    private List<String> tags;

    public void fromPost(Post post) {
        id = post.getId();
        description = post.getDescription();
        authorName = post.getAuthor().getUsername();
        photoUrl = post.getPhoto().getUrl();
        tags = post.getTags();
    }
}
