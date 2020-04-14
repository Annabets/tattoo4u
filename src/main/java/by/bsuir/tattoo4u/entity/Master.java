package by.bsuir.tattoo4u.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "masters")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Master extends BaseEntity {

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Column(name = "rating")
    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Studio job;
}
