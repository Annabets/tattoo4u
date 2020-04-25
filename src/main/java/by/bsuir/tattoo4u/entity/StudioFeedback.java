package by.bsuir.tattoo4u.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class StudioFeedback extends BaseEntity {
    private String feedback;

    private Double rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studio_id")
    private Studio studio;

    private String username;

    private String userPhoto;

    public StudioFeedback() {
    }

    public StudioFeedback(String feedback, Double rating) {
        this.feedback = feedback;
        this.rating = rating;
    }

    public StudioFeedback(Double rating) {
        this.rating = rating;
    }
}
