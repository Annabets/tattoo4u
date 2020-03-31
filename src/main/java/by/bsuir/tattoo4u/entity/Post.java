package by.bsuir.tattoo4u.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Post extends BaseEntity{
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @ElementCollection
    private List<String> tags;

    public Post(String description, User author, List<String> tags) {
        this.description = description;
        this.author = author;
        this.tags = tags;
    }
}