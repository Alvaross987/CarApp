package car.app.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;

@Entity
@Table(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@NotNull(message = "BRAND CANNOT BE NULL")
	@ManyToOne()
	@JoinColumn(name= "brand_id", updatable = true)
	private Brand brand;
	
	@Column(name = "registration", nullable = false)
	@NotNull(message = "REGISTRATION CANNOT BE NULL")
	private Timestamp registration;

	@NotNull(message = "COUNTRY CANNOT BE NULL")
	@ManyToOne
	@JoinColumn(name= "country_id", updatable = true)
	private Country Country;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "checked", nullable = false)
	private Integer checked;
	
	@Column(name = "color")
	private String color;

	@Hidden
	@JsonIgnore
	@Column(name = "created_at")
	private Timestamp Created_at;
	
	@Hidden
	@JsonIgnore
	@Column(name = "last_updated")
	private Timestamp Last_Updated;

	public Car() {
		super();
	}

	public Car(int i) {
		this.id = i;
	}

	public Car(int id, Brand brand, Timestamp registration, Country country, Timestamp created_at,
			Timestamp last_Updated) {
		
		super();
		this.id = id;
		this.brand = brand;
		this.registration = registration;
		this.Country = country;
		this.Created_at = created_at;
		this.Last_Updated = last_Updated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Timestamp getRegistration() {
		return registration;
	}
	
	public void setRegistration(Timestamp registration) {
		this.registration = registration;
	}

	public Country getCountry() {
		return Country;
	}

	public void setCountry(Country country) {
		Country = country;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
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
		this.checked = 0;
		this.Created_at = new Timestamp(new Date().getTime());
		this.Last_Updated = new Timestamp(new Date().getTime());
	}

	@PreUpdate
	public void preUpdate() {
		this.Last_Updated = new Timestamp(new Date().getTime());
	}

}
