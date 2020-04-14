package by.bsuir.tattoo4u.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Studio extends BaseEntity {

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    private Double rating;

    private String description;

    private String address;

    //    @OneToMany(fetch = FetchType.EAGER)
//    private List<Contact> contacts;
    private String contact;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private List<User> masters;

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
