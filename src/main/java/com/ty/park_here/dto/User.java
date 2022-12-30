package com.ty.park_here.dto;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.ty.park_here.util.AesEncryption;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	@Convert(converter = AesEncryption.class)
	private String password;
	@NotNull
	private long phone;
	@NotNull
	private String address;
	@NotNull
	private String role;
}
