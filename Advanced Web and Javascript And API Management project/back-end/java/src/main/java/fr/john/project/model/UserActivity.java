package fr.john.project.model;

import java.sql.Timestamp;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "public", name = "user_activities")
@Getter
@Setter
@Access(AccessType.FIELD)
public class UserActivity
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  @Column(name = "activity_ts", nullable = false)
  private Timestamp activityTs;
  
  @Column(name = "user_id", nullable = false)
  private int userId;
  
  @Column(name = "movie_id", nullable = false)
  private int movieId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(insertable = false, updatable = false)
  private User user;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(insertable = false, updatable = false)
  private Movie movie;
}