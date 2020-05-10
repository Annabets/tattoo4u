package by.bsuir.tattoo4u.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "master_comment")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MasterComment extends BaseEntity implements Comparable<MasterComment>{

    @Column(name = "rating")
    private Double rating;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "master_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Master master;

    @Override
    public int compareTo(MasterComment o) {
        return getId().compareTo(o.getId());
    }
}
