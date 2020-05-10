package by.bsuir.tattoo4u.dto.response;

import by.bsuir.tattoo4u.entity.Comment;
import lombok.Data;

@Data
public class CommentResponseDto {
    private Long id;
    private String text;
    private String author;
    private String date;
    private String photoUrl;

    public void fromComment(Comment comment){
        id = comment.getId();
        text = comment.getComment();
        author = comment.getAuthor().getUsername();
        date = comment.getDate().toString();
        photoUrl = comment.getAuthor().getPhoto().getUrl();
    }
}
