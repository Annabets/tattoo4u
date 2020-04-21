package by.bsuir.tattoo4u.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "orders")
public class Order extends BaseEntity {
    private boolean finish;

    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_id")
    @NotNull(message = "Photo must not be null")
    private Photo photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    public Order() {
        this.finish = false;
    }

    public Order(String description) {
        this.description = description;
        this.finish = false;
    }
}
