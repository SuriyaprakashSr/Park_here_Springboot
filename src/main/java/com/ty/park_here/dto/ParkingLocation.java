package com.ty.park_here.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ParkingLocation {

	@Id
	@GenericGenerator(name = "id_generations", strategy = "com.ty.park_here.customgeneration.CustomParkingLoacationId")
	@GeneratedValue(generator = "id_generations")
	private String parkingLocationId;
	
	@NotBlank(message = "Location can no be blank")
	private String locationName;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ParkingSpace> parkingSpaces;

}
