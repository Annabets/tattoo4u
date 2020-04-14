package by.bsuir.tattoo4u.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Photo extends BaseEntity {
    @NotNull(message = "Url must not be null")
    private String url;
}
