package com.dollop.app.dtos;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.dollop.app.entity.Address;
import com.dollop.app.entity.Albums;
import com.dollop.app.entity.Comment;
import com.dollop.app.entity.Post;
import com.dollop.app.entity.ToDoList;
import com.dollop.app.entity.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto 
{
	private Integer userId;
	private String userFirstName;
	private String userLastName;
	
	@Size(min = 5, max = 100, message = "invalid Name !")
	@Column(unique = true)
	private String userName;
	
	private String userPassword;
	@Column(unique = true)
	private String userEmail;
	
	private String userPhone;
	
	private String userWebsite;
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
	
	//private boolean isActive = true;
	
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
	
	@OneToMany
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
