package car.app.entity;


import java.sql.Timestamp;

import javax.persistence.Entity;



@Entity
public class Car {
    
	private int id;
    private String name;
    private Timestamp Registration;
    private String Country;
    private Timestamp Created_at;
    private Timestamp Last_Updated;
    
    
    public Car(int i) {
		this.id=i;
	}
    
	public Car(int id, String name, Timestamp registration, String country, Timestamp created_at,
			Timestamp last_Updated) {
		super();
		this.id = id;
		this.name = name;
		Registration = registration;
		Country = country;
		Created_at = created_at;
		Last_Updated = last_Updated;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getRegistration() {
		return Registration;
	}
	public void setRegistration(Timestamp registration) {
		Registration = registration;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public Timestamp getCreated_at() {
		return Created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		Created_at = created_at;
	}
	public Timestamp getLast_Updated() {
		return Last_Updated;
	}
	public void setLast_Updated(Timestamp last_Updated) {
		Last_Updated = last_Updated;
	}
    
    
    
    
}
