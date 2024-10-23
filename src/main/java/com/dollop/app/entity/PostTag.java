package com.dollop.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post_tag_table")
public class PostTag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postTagId;
	
	@ManyToOne
	//@JsonIgnore
	@JoinColumn(name = "postId")
    private Post post;
	
	@ManyToOne
	@JsonIgnoreProperties(value = "tags")
	@JoinColumn(name = "tagsId")
	private Tags tag;
	
	private boolean isActive = true;
}
