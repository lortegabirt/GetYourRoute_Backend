package birt.eus.getyourroutebackend.model;

import birt.eus.getyourroutebackend.validation.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Document("users")
public class User {

  @Id
  private String id;
  @NotBlank
  private String name;
  private String lastName;
  @Email
  @NotBlank
  @UniqueEmail
  private String email;
  @NotBlank
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Override
  public String toString() {
	return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", password="
			+ password + "]";
  }

}
