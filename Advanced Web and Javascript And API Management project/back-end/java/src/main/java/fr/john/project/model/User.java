package fr.john.project.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceException;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "users")
@Getter
@Setter
@Access(AccessType.FIELD)
public class User
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  @Column(name = "username", nullable = false, unique = true, length = 30)
  @Size(min = 3, message = "username should have min 6 characters")
  private String username;
  
  @Column(name = "email", nullable = false, unique = true, length = 50)
  @Size(min = 3, message = "email should have min 6 characters")
  private String email;
  
  @Column(name = "password", nullable = false)
  @Size(min = 6, message = "Password should have min 6 characters")
  private String password;
  
  @OneToOne
  @PrimaryKeyJoinColumn
  private Contact contact;
  
  @OneToOne
  @PrimaryKeyJoinColumn
  private Address address;
  
  @PrePersist
  @PreUpdate
  public void normalize()
  {
    if (password == null)
    {
      throw new PersistenceException("Password must not be null");
    }
    // even if it's null the program will take it  because it will be replaced by hash
    
    //    password = DigestUtils.sha256Hex(password);
  }
}