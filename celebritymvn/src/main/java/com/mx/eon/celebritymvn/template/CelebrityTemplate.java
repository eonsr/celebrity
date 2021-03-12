package com.mx.eon.celebritymvn.template;

import static com.mx.eon.celebritymvn.constants.CelebrityConstants.MSG_ERROR_LIST_EMPTY_OR_NULL;
import static com.mx.eon.celebritymvn.constants.CelebrityConstants.MSG_ERROR_NO_CELEBRITIES;

import java.util.List;
import java.util.Map;

import com.mx.eon.celebritymvn.dto.PersonDTO;

import lombok.extern.log4j.Log4j;

/**
 * Template para Celebrity, continee la funcionaldiad para encontrar una 
 * celebridad
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
public abstract class CelebrityTemplate {
	
	/**
	 * Metodo para encontrar celebridades en una lista de personas 
	 * 
	 * @param peopleList lista de {@link PersonDTO}
	 * @return String con celebridades
	 */
	public String findCelebrities(List<PersonDTO> peopleList) {
		
		log.info("findCelebrities");
		
		String celebrities = null;
		
		if (peopleList != null && !peopleList.isEmpty()) {
			
			Map<Boolean, List<PersonDTO>> peopleMap = dividePeople(peopleList);
			
			List<PersonDTO> possibleCelebList = peopleMap.get(true);
			peopleList = peopleMap.get(false);
			
			List<PersonDTO> celebList = extractCelebrities(possibleCelebList, peopleList);
			
			if (celebList != null && !celebList.isEmpty()) {
				celebrities = getCelebritiesStr(celebList);					
			} else {
				log.error(MSG_ERROR_NO_CELEBRITIES);
				celebrities = MSG_ERROR_NO_CELEBRITIES;
			}
				
			
		} else {
			log.error(MSG_ERROR_LIST_EMPTY_OR_NULL);
			celebrities = MSG_ERROR_LIST_EMPTY_OR_NULL;
		}
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		return celebrities;
		
	}

	/**
	 * Metodo para dividir a la gente
	 * 
	 * @param peopleList lista de gente
	 * @return
	 */
	public abstract Map<Boolean, List<PersonDTO>> dividePeople(List<PersonDTO> peopleList);

	/**
	 * Metodo para extraer celebridades
	 * 
	 * @param possibleCelebList lista de {@link PersonDTO}
	 * @param peopleList lista de {@link PersonDTO} deben ser personas normales (no artistas)
	 * @return lista de {@link PersonDTO} con celebridades
	 */
	public abstract List<PersonDTO> extractCelebrities(List<PersonDTO> possibleCelebList,
			List<PersonDTO> peopleList);

	/**
	 * Metodo apra geenrar un String con celebridades
	 * 
	 * @param celebList lista de {@link PersonDTO}
	 * @return String con celebridades
	 */
	public abstract String getCelebritiesStr(List<PersonDTO> celebList);

}
