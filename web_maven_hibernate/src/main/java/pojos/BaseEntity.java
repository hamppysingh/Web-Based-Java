package pojos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.*;

@MappedSuperclass //Mandatory class level annotation to ttell hibernate 
//following class is a super class containing common fields 
//Do not create any table for it
public class BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  public Integer getId() {
	  return id;
  }
  
}
