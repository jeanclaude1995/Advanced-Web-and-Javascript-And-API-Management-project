package fr.john.project.loginController;

import fr.john.project.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
  extends JpaRepository<User, Long>
{
  @Query(value = "select r from User r where r.username = :username and r.password = :password")
  List<User> findByCredentials(@Param("username") String username, @Param("password") String password);
  
  @Query(value = "select r from User r where r.username = :username or r.email = :email or r.password = :password")
  List<User> findBy(
    @Param("username") String username,
    @Param("email") String email,
    @Param("password") String password
  );
  
  @Query(value = "select r from User r where r.username = :username")
  List<User> findByUsername(@Param("username") String username);
}