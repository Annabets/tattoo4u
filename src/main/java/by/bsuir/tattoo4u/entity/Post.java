package by.bsuir.tattoo4u.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Post extends BaseEntity {
    @Size(max = 2048, message = "Description size should be no more than 2048 characters")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotNull(message = "Author must not be null")
    private User author;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_id")
    @NotNull(message = "Photo must not be null")
    private Photo photo;

    @ElementCollection
    @NotNull(message = "Tags must not be null")
    private List<String> tags;

    public Post(String description, User author, List<String> tags) {
        this.description = description;
        this.author = author;
        this.tags = tags;
    }
}