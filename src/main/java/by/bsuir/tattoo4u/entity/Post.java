package by.bsuir.tattoo4u.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> likes;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Set<Comment> comments;

    public Post(
            @Size(max = 2048, message = "Description size should be no more than 2048 characters") String description,
            @NotNull(message = "Author must not be null") User author,
            @NotNull(message = "Photo must not be null") Photo photo,
            @NotNull(message = "Tags must not be null") List<String> tags
    ) {
        this.description = description;
        this.author = author;
        this.photo = photo;
        this.tags = tags;
    }
}