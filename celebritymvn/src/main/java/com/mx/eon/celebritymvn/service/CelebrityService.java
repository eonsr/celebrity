package com.mx.eon.celebritymvn.service;

import java.util.List;

import com.mx.eon.celebritymvn.dto.PersonDTO;

/**
 * Interfaz de servicios para celebridad
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
public interface CelebrityService {

	/**
	 * Metodo para encontrar celebridades en una lista de personas 
	 * 
	 * @param peopleList lista de {@link PersonDTO}
	 * @return String con celebridades
	 */
	public String findCelebrities(List<PersonDTO> peopleList);
	
}
