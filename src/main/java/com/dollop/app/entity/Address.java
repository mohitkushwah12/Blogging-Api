package com.dollop.app.entity;


import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="address_table")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	
	@NotBlank(message = "street is required")
	private String street;
	@NotBlank(message = "suite is required")
	private String suite;
	@NotBlank(message = "city is required")
	private String city;
	@NotBlank(message = "zipCode is required")
	private String zipCode;
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
	private Integer createdBy;
	private Integer updatedBy;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	private boolean isActive = true;
	
}
