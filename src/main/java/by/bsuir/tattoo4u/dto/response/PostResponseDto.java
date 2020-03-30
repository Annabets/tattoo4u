package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Post;
import lombok.Data;

@Data
public class PostResponseDto {
    private String description;
    private String authorName;
    private String photoUrl;

    public void fromPost(Post post) {
        description = post.getDescription();
        authorName = post.getAuthor().getUsername();
        photoUrl = post.getPhoto().getUrl();
    }
}
