package fr.john.project.dto;

import fr.john.project.model.Contact;

import java.sql.Date;

public class ContactView
{
  
  private final Long userId;
  
  private final String firstname;
  
  private final String lastname;
  
  private final Date birthdate;
  
  private final String gender;
  
  public ContactView(Contact info)
  {
    this.userId = info.getUserId();
    this.firstname = info.getFirstname();
    this.lastname = info.getLastname();
    this.birthdate = info.getBirthdate();
    this.gender = info.getGender();
  }
}
