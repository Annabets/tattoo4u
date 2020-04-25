package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Post;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostResponseDto {
    private Long id;
    private String description;
    private String authorName;
    private String photoUrl;
    private List<String> tags;

    public PostResponseDto() {
    }

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.description = post.getDescription();
        this.authorName = post.getAuthor().getUsername();
        this.photoUrl = post.getPhoto().getUrl();
        this.tags = post.getTags();
    }

    public void fromPost(Post post) {
        id = post.getId();
        description = post.getDescription();
        authorName = post.getAuthor().getUsername();
        photoUrl = post.getPhoto().getUrl();
        tags = post.getTags();
    }
}
