package by.bsuir.tattoo4u.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tokens")
@Data
@EqualsAndHashCode(callSuper = true)
public class Token extends BaseEntity {

    @Column(name = "token")
    private String token;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }
}
