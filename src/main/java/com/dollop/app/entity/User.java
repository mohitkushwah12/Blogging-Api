package com.dollop.app.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_table")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@NotBlank(message = "First Name Is Required")
	private String userFirstName;
	@NotBlank(message = "Last Name Is Required")
	private String userLastName;
	
	@Size(min = 5, max = 100, message = "invalid Name !")
	@Column(unique = true)
	private String userName;
	
	//@Size(min = 4, max = 30, message = "invalid Password !")
	@NotBlank(message = "please enter password !")
	private String userPassword;
	@Column(unique = true)
	private String userEmail;
	
	@NotBlank(message = "Phone Number Is Required")
	private String userPhone;
	
	@NotBlank(message = "Website Is Required")
	private String userWebsite;
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
	
	private boolean isActive = true;
	
	@OneToMany(cascade =  CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value = "user")
	@JoinColumn(name = "userId")
	private List<Address> userAddress;
	
	@OneToMany
	@JsonIgnoreProperties(value = "user")
	@JoinColumn(name = "userId")
	private List<ToDoList> usertodoList;
	
	@OneToMany
	@JsonIgnoreProperties(value = "user")
	@JoinColumn(name = "userId")
	private List<Albums> userAlbums;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "user")
	@JoinColumn(name = "userId")
	private Set<UserRoles> userRoles;
	
	@OneToMany(cascade =  CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value = "user")
	@JoinColumn(name = "userId")
	private List<Post> userPost;
	
	@OneToMany
	@JsonIgnoreProperties(value = "user")
	@JoinColumn(name = "userId")
	private List<Comment> userComments;
	
}
