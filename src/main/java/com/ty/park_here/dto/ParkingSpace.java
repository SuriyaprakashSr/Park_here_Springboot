package com.ty.park_here.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ParkingSpace {
	
	@Id
	@GenericGenerator(name = "id_generate", strategy = "com.ty.park_here.customgeneration.CustomParkinSpaceId")
	@GeneratedValue(generator = "id_generate")
	private String parkingSpaceId;

	@NotNull(message = "Name can not be blank.. Kindly fill the valid name")
	private String parkingSpaceName;
	
	
	private int availableSpace;
	
	@NotNull(message = "Utilized space can not be null")
	private int utilizedSpace;
	
	@NotNull(message = "Totel space can not be null")
	private int totalSpace;
	
	@NotNull(message = "Totel space can not be null")
	private int rentPerHour;
	
	
}
