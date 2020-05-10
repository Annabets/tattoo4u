package by.bsuir.tattoo4u.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Studio extends BaseEntity {

    @Column
    private boolean confirmation;

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User owner;

    private Double rating;

    private String description;

    private String address;

    private String contact;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private List<Master> masters;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_id")
    @NotNull(message = "Photo must not be null")
    private Photo photo;

    @ManyToMany(mappedBy = "favourites", fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> subscribers;

    @OneToMany(mappedBy = "studio", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders;

    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<StudioFeedback> feedbacks;

    public Studio() {
        this.confirmation = false;
        masters = new ArrayList<>();
    }

    public Studio(String name, String description, String address, String contact) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.contact = contact;
        this.rating = 0.0;
        this.confirmation = false;
    }
}
