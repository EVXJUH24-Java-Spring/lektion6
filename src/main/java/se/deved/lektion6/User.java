package se.deved.lektion6;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "security_users")
@Data
@NoArgsConstructor
public class User {

    @Id
    private UUID id;

    private String name;
    private String password;

    public User(String name, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.password = password;
    }
}
