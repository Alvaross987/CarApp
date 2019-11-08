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

import io.swagger.v3.oas.annotations.Hidden;

@Entity
@Table(name="car.user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "username", nullable = false)
	@NotNull
	private String username;
	
	@Column(name = "password", nullable = false)
	@NotNull
	@Hidden
	@JsonIgnore
	private String password;
	
	@Column(name = "isadmin", nullable = false)
	@NotNull
	@Hidden
	@JsonIgnore
	private Integer isadmin;
	
	@Column(name = "token")
	@Hidden
	@JsonIgnore
	private String token;
	
	@Hidden
	@Column(name = "created_at")
	@JsonIgnore
	private Timestamp created_At;
	
	@Hidden
	@Column(name = "last_updated")
	@JsonIgnore
	private Timestamp last_Updated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Integer isAdmin) {
		this.isadmin = isAdmin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Timestamp created_At) {
		this.created_At = created_At;
	}

	public Timestamp getLast_Updated() {
		return last_Updated;
	}

	public void setLast_Updated(Timestamp last_Updated) {
		this.last_Updated = last_Updated;
	}

	@PrePersist
	public void prePersist() {
		isadmin = 0;
		created_At = new Timestamp(new Date().getTime());
		last_Updated = new Timestamp(new Date().getTime());
	}

	@PreUpdate
	public void preUpdate() {
		last_Updated = new Timestamp(new Date().getTime());
	}

	
	
}
