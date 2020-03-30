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
public class Studio extends BaseEntity {

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    private Double rating;

    private String description;

    private String address;

    //    @OneToMany(fetch = FetchType.EAGER)
//    private List<Contact> contacts;
    private String contact;

    public Studio() {
    }

    public Studio(String name, String description, String address, String contact) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.contact = contact;
        this.rating = 0.0;
    }
}
