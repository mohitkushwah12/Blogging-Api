package com.dollop.app.entity;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "role_table")
public class Roles {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rolesId;
	
	private boolean isActive = true;
	
	@Enumerated(EnumType.STRING)
	private RoleName rolesName;
	
	@OneToMany
	@JoinColumn(name = "rolesId")
	private List<UserRoles> userRoles;
}
