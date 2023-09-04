package ar.com.semillero.semillatronalfa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Entity
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Setter(value = AccessLevel.NONE)
    private String id;
    
    @Column(columnDefinition = "Bit(1) default true")
    private boolean active = true;
    
    private String username;
    
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
