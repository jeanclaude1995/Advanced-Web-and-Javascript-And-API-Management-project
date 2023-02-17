package fr.john.project.loginController;

import fr.john.project.dto.Role;
import fr.john.project.dto.UserPrincipal;
import fr.john.project.dto.UserView;
import fr.john.project.model.User;

import java.util.List;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController
{
  @Autowired
  private UserService userService;
  
  @Autowired
  private JwtDecoder jwtDecoder;
  
  @GetMapping("/users")
  @RolesAllowed(Role.USER_ADMIN)
  public List<User> findAll()
  {
    return userService.findAll();
  }
  
  @GetMapping("/user/{userId}")
  @RolesAllowed(Role.USER_ADMIN)
  public Optional<User> findById(@PathVariable(value = "userId") Long userId)
  {
    return userService.findById(userId);
  }
  
  @GetMapping("/user/current")
  @RolesAllowed(Role.USER)
  public UserPrincipal findCurrent(@RequestHeader(HttpHeaders.AUTHORIZATION) String token)
  {
    return UserPrincipal.fromSubject(jwtDecoder.decode(token.replace("Bearer ", "")).getSubject());
  }
  
  @PostMapping(path = "/public/signup", consumes = {"application/json"})
  public UserView signUp(@RequestBody User user)
  {
    return new UserView(userService.signUp(user));
  }



}