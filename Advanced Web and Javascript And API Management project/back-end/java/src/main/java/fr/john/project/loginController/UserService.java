package fr.john.project.loginController;

import fr.john.project.model.User;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
  @Autowired
  private UserRepository repository;
  
  public User signUp(User user)
  {
    
    if (repository.findBy(user.getUsername(), user.getEmail(), user.getPassword()).isEmpty())
    {
      
      return repository.save(user);
    }
    if (user.getPassword().isEmpty())
    {
      throw new PersistenceException("Password must not be null");
    }
    else
    {
      throw new EntityExistsException("User already exists");
    }
  }
  
  public List<User> findAll()
  {
    return repository.findAll();
  }
  
  public List<User> findByCredentials(String username, String password)
  {
    if (username.isEmpty() || password.isEmpty())
    {
      return Collections.emptyList();
    }
    return repository.findByCredentials(username, password);
  }
  
  public Optional<User> findById(Long userId)
  {
    return repository.findById(userId);
  }
}