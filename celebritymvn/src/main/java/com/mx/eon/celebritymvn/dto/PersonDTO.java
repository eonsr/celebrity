package com.mx.eon.celebritymvn.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
	
	/**
	 * Nombre de la persona
	 */
	private String name;
	
	/**
	 * Listas de personas conocidas
	 */
	private List<String> knownPeopleList;

}
