package com.ty.park_here.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Email(message = "Enter the valid email")
	private String email;
	
	@NotBlank
	@Size(min = 4, max = 10, message = "Password must be more than 4 and less them 10")
	private String password;
	
	@NotNull(message = "Phone nuber can not be empty")
	private long phone;
	
	@NotBlank(message = "address can not be blank")
	private String address;
	
	@OneToMany
    private List<ParkingLocation> parkingLocationList;
	
	@OneToMany
	private List<Admin> admins;
}
