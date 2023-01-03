
package com.ty.park_here.dto;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ty.park_here.util.AesEncryption;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name can not be blank.. Kindly fill the valid name")
	private String name;
	
	@Email(message = "Kindly enter the valid email address")
	private String email;
	
	@NotNull
	@Convert(converter = AesEncryption.class)
	@Size(min=4, max=10 ,message = "Password must be more than 4 and less them 10")
	private String password;
	
	@NotNull(message = "Phone nuber can not be empty")
	private long phone;
	
	@NotBlank(message = "address can not be blank")
	private String address;
	
	@NotBlank(message =  "Role can not be blank")
	private String role;
}
