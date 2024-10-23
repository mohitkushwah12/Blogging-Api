package com.dollop.app.entity;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "post_table")
public class Post 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Size(min = 5, max = 30, message = "+invalid Name !")
	@NotBlank(message = "post name is Required")
	@Column(unique = true)
	private String postName;
	
	@NotBlank(message = "post body is required")
	@Size(min = 20, max = 150, message = "invalid name")
	private String postBody;
	
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
	private Integer createdBy;
	private Integer updatedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToMany
	@JsonIgnoreProperties(value = {"post", "user"})
	@JoinColumn(name = "postId")
	private List<Comment> postComments;
	
	@OneToMany
	@JsonIgnoreProperties(value = "post")
	@JoinColumn(name = "postId")
	private List<PostTag> postTag;
	
	private boolean isActive = true;

}
