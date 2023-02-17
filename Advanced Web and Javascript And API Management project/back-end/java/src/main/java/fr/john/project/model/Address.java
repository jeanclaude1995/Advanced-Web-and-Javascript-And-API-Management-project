package fr.john.project.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

@Entity
@Table(schema = "public", name = "addresses")
@Getter
@Setter
@Access(AccessType.FIELD)
public class Address
{
  @Id
  @Column(name = "user_id")
  private Long userId;
  
  @Column(name = "country", nullable = false, length = 30)
  private String country;
  
  @Column(name = "area", nullable = false, length = 20)
  private String area;
  
  @Column(name = "city", nullable = false, length = 20)
  private String city;
  
  @Column(name = "street", nullable = false, length = 40)
  private String street;
  
  @Column(name = "number", nullable = false, length = 10)
  private String number;
  
  @PrePersist
  @PreUpdate
  public void normalize()
  {
    country = StringUtils.lowerCase(country);
    area = StringUtils.lowerCase(area);
    city = StringUtils.lowerCase(city);
    street = StringUtils.lowerCase(street);
    number = StringUtils.lowerCase(number);
  }
  
  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  private User user;
}