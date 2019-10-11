package car.app.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "car.car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	@NotNull(message = "NAME CANNOT BE NULL")
	private String name;
	
	@Column(name = "registration")
	private Timestamp Registration;

	@Column(name = "country")
	@NotNull(message = "COUNTRY CANNOT BE NULL")
	private String Country;

	@Column(name = "created_at")
	private Timestamp Created_at;

	@Column(name = "last_updated")
	private Timestamp Last_Updated;

	public Car() {
		super();
	}

	public Car(int i) {
		this.id = i;
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
	@JsonIgnore
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
	@JsonIgnore
	public Timestamp getCreated_at() {
		return Created_at;
	}
	
	public void setCreated_at(Timestamp created_at) {
		Created_at = created_at;
	}

	@JsonIgnore
	public Timestamp getLast_Updated() {
		return Last_Updated;
	}
	
	public void setLast_Updated(Timestamp last_Updated) {
		Last_Updated = last_Updated;
	}

	@PrePersist
	public void prePersist() {
		Created_at = new Timestamp(new Date().getTime());
		Registration = new Timestamp(new Date().getTime());
		Last_Updated = new Timestamp(new Date().getTime());
	}

	@PreUpdate
	public void preUpdate() {
		Last_Updated = new Timestamp(new Date().getTime());
	}

}
