package fr.john.project.model;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
  schema = "public", name = "movies",
  uniqueConstraints = @UniqueConstraint(name = "unique_external_movie", columnNames = {"eid", "source"})
)
@Getter
@Setter
@Access(AccessType.FIELD)
public class Movie
{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  @Column(name = "eid", nullable = false)
  private Long eid;
  
  @Column(name = "source", nullable = false, length = 2)
  private String source;
  
  @Column(name = "title", nullable = false, length = 50)
  private String title;
  
  @Column(name = "image")
  private String image;
  
  @Column(name = "release_date", nullable = false)
  private Date releaseDate;
}