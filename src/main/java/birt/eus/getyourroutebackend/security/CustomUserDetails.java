package birt.eus.getyourroutebackend.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

  private static final long serialVersionUID = 2038249803159009748L;
  
  @Getter
  private final String id;

  public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String id) {
    super(username, password, authorities);
    this.id = id;
  }
}
