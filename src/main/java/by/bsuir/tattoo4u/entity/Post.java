package by.bsuir.tattoo4u.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    public Post() {
    }

    public Post(String description, User author, String filename) {
        this.description = description;
        this.author = author;
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id.equals(post.id) &&
                description.equals(post.description) &&
                author.equals(post.author) &&
                filename.equals(post.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, author, filename);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", filename='" + filename + '\'' +
                '}';
    }
}