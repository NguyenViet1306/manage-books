package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  @JsonIgnore
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();
  
  private Boolean blockUser = false
  
  ;

  public User() {
  }

  
  
  public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(max = 120) String password, Set<Role> roles, Boolean blockUser) {
	super();
	this.id = id;
	this.username = username;
	this.email = email;
	this.password = password;
	this.roles = roles;
	this.blockUser = blockUser;
}

public User(String username, String email, String password, Boolean blockUser) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.blockUser = blockUser;
  }

  public Long getId() {
    return id;
  }

  public Boolean getBlockUser() {
	return blockUser;
}

public void setBlockUser(Boolean blockUser) {
	this.blockUser = blockUser;
}

public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }



@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", roles="
			+ roles + ", BlockUser=" + blockUser + "]";
}
  
  
}
