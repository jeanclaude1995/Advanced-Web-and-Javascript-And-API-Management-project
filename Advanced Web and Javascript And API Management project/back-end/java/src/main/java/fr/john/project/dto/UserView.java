package fr.john.project.dto;

import fr.john.project.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserView
{
  private Long id;
  private String username;
  private String email;
  
  private String password;
  
  public UserView(User user)
  {
    this.id = user.getId();
    this.username = user.getUsername();
    this.email = user.getEmail();
    this.password = user.getPassword();
  }
}