package fr.john.project.model;

import java.sql.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "contacts")
@Getter
@Setter
@Access(AccessType.FIELD)
public class Contact
{
  @Id
  @Column(name = "user_id")
  private Long userId;
  
  @Column(name = "firstname", nullable = false, length = 30)
  private String firstname;
  
  @Column(name = "lastname", nullable = false, length = 30)
  private String lastname;
  
  @Column(name = "birthdate", nullable = false)
  private Date birthdate;
  
  @Column(name = "gender", nullable = false, length = 1)
  private String gender;
  
  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  private User user;
}