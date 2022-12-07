package com.ty.park_here.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	private String password;
	@NotNull
	private long phone;
	@NotNull
	private String address;
	@NotNull
	private String role;
}
