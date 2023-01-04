package com.ty.park_here.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ParkingSpace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Name can not be blank.. Kindly fill the valid name")
	private String parkingSpaceName;
	
	@NotBlank(message = "Available space can not be null")
	private int availableSpace;
	
	@NotBlank(message = "Utilized space can not be null")
	private int utilizedSpace;
	
	@NotNull(message = "Totel space can not be null")
	private int totalSpace;
	
	@NotNull(message = "Totel space can not be null")
	private int rentPerHour;
	
	private String vehicleNo;
	
	
}
