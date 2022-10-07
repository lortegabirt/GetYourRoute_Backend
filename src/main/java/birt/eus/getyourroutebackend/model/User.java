package birt.eus.getyourroutebackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {

    @Id
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String password;

}
