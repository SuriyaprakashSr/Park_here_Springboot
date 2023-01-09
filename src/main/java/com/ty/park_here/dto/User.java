package com.ty.park_here.dto;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.ty.park_here.util.AesEncryption;

import lombok.Data;

@Data
@Entity
public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GenericGenerator(name = "id_generation", strategy = "com.ty.park_here.customgeneration.CustomUserId")
	@GeneratedValue(generator = "id_generation")
	private String id;
	
	@NotBlank(message = "Name can not be blank.. Kindly fill the valid name")
	private String name;
	
	@Email(message = "Kindly enter the valid email address")
	private String email;
	
	@NotNull
	@Convert(converter = AesEncryption.class)
	@Size(min=4, max=10 ,message = "Password must be more than 4 and less them 10")
	private String password;
	
	@NotNull(message = "Phone number can not be null")
	private long phone;
	
	@NotBlank(message = "address can not be blank")
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Roles roles;
	
}
