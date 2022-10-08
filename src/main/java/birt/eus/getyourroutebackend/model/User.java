package birt.eus.getyourroutebackend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Document("users")
public class User {

  @Id
  private String id;
  @NotBlank
  private String name;
  private String lastName;
  @Email
  @NotBlank
  private String email;
  @NotBlank
  private String password;

}
