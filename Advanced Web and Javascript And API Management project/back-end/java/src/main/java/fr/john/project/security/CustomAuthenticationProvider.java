package fr.john.project.security;

import fr.john.project.model.User;
import fr.john.project.dto.Role;
import fr.john.project.dto.UserPrincipal;
import fr.john.project.loginController.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider
  implements AuthenticationProvider
{
  @Autowired
  private UserService service;
  
  @Override
  public Authentication authenticate(Authentication authentication)
  throws AuthenticationException
  {
    String name = authentication.getName();
    String password = authentication.getCredentials().toString();
    //    String hashed = DigestUtils.sha256Hex(Optional.ofNullable(password).orElse(""));
    
    List<User> userList = service.findByCredentials(name, password);
    
    if (!userList.isEmpty())
    {
      UserPrincipal principal = new UserPrincipal(userList.get(0));
      return new UsernamePasswordAuthenticationToken(principal, password, List.of(new Role(Role.USER)));
    }
    else
    {
      return null;
    }
  }
  
  @Override
  public boolean supports(Class<?> authentication)
  {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}