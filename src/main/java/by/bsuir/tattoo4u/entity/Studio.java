package by.bsuir.tattoo4u.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Studio extends BaseEntity {

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

    public Studio() {
        masters = new ArrayList<>();
    }

    public Studio(String name, String description, String address, String contact) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.contact = contact;
        this.rating = 0.0;
    }
}
