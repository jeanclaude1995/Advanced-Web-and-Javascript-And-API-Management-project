package fr.john.project.dto;

import fr.john.project.model.User;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPrincipal
{
  private final Long id;
  private final String username;
  private final String email;
  private final Instant loginTime;
  
  public UserPrincipal(User user)
  {
    this.id = user.getId();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.loginTime = Instant.now();
  }
  
  public String subject()
  {
    return String.format("%s,%s,%s,%s", id, username, email, loginTime);
  }
  
  public static UserPrincipal fromSubject(String subject)
  {
    String[] split = subject.split(",");
    return new UserPrincipal(Long.parseLong(split[0]), split[1], split[2], Instant.parse(split[3]));
  }
}