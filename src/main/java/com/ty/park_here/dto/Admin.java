package com.ty.park_here.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Admin name can not be blank")
	private String name;
	
	@Email(message = "Enter valid email")
	private String email;
	
	@NotNull(message = "Enter the valid phone number")
	private long phone;
	
	@NotBlank
	@Size(min = 4, max = 10, message = "Password must be more than 4 and 10 digit")
	private String password;
	
	@NotBlank(message = "Address can not be blank")
	private String address;

}
