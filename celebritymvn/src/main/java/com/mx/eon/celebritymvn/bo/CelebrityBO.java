package com.mx.eon.celebritymvn.bo;

import static com.mx.eon.celebritymvn.constants.CelebrityConstants.MSG_ERROR_NO_CELEBRITIES;
import static com.mx.eon.celebritymvn.constants.CelebrityConstants.STR_SEPARATOR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mx.eon.celebritymvn.dto.PersonDTO;
import com.mx.eon.celebritymvn.template.CelebrityTemplate;

import lombok.extern.log4j.Log4j;

/**
 * Clase de negocio, implementa {@link CelebrityTemplate}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@Component
public class CelebrityBO extends CelebrityTemplate {
	

	@Override
	public Map<Boolean, List<PersonDTO>> dividePeople(List<PersonDTO> peopleList) {
		
		log.info("dividePeople");
		
		Map<Boolean, List<PersonDTO>> peopleMap = null;
		
		if (peopleList != null && !peopleList.isEmpty()) {
			
			List<PersonDTO> possibleCelebList = new ArrayList<>();
			List<PersonDTO> normalPeopleList = new ArrayList<>();
			
			peopleMap = new HashMap<>();
			
			for (PersonDTO personDTO : peopleList) {
				
				if (personDTO.getKnownPeopleList() == null || personDTO.getKnownPeopleList().isEmpty()) {
					
					if (personDTO.getName() != null && !personDTO.getName().isEmpty()) {
						log.info(personDTO + " podria ser una celebridad");
						possibleCelebList.add(personDTO);						
					} else {
						log.error("el nombre de la persona no es valido");
					}
					
				} else {
					log.info(personDTO + " No es una celebridad");
					normalPeopleList.add(personDTO);
				}
				
			}
			
			peopleMap.put(true, possibleCelebList);
			peopleMap.put(false, normalPeopleList);
			
		} else {
			log.error("peopleList es nula o esta vacia");
		}
		
		log.info("Mapa de division de personas " + peopleMap);
		
		return peopleMap;
		
	}

	@Override
	public List<PersonDTO> extractCelebrities(List<PersonDTO> possibleCelebList, List<PersonDTO> peopleList) {
		
		log.info("extractCelebrities");
		
		log.debug("Posibles celebridades " + possibleCelebList);
		log.debug("Personas normales " + peopleList);
		
		List<PersonDTO> celebList = null;
		
		if (possibleCelebList != null && !possibleCelebList.isEmpty() &&
				peopleList!= null && !peopleList.isEmpty()) {
			
			celebList = new ArrayList<>();
			
			for (PersonDTO personDTO : possibleCelebList) {
				
				if (isCelebrity(personDTO.getName(), peopleList)) {
					celebList.add(personDTO);
				} else {
					log.error(personDTO + " No es celebridad");
				}
				
			}
			
		} else {
			log.error("Los parametros no son validos");
		}
		
		log.info("Lista de celebridades generada " + celebList);
		
		return celebList;
		
	}

	@Override
	public String getCelebritiesStr(List<PersonDTO> celebList) {
		
		log.info("getCelebritiesStr");
		
		String celebrities = null;
		
		if (celebList != null && !celebList.isEmpty()) {
			
			StringBuilder builder = new StringBuilder();
			
			for (PersonDTO personDTO : celebList) {
				
				if (personDTO.getName() != null && !personDTO.getName().isEmpty()) {
					builder.append(STR_SEPARATOR);
					builder.append(personDTO.getName());
					builder.append(STR_SEPARATOR);
				} else {
					log.error("Nombre de celebridad no valido");
				}
				
			}
			
			celebrities = builder.toString();
			
		} else {
			log.error(MSG_ERROR_NO_CELEBRITIES);
		}
		
		log.info("String generado [" + celebrities + "]");
		
		return celebrities;
		
	}
	
	/*
	 * ========================================================================
	 *                           Metodos privados
	 * ========================================================================
	 */

	/**
	 * Metodo que define si el nombre que entro pertenece a una persona que es celebridad
	 * 
	 * @param name nombre de la persona
	 * @param peopleList lista de personas
	 * @return true si es elebridad, false de lo contrario
	 */
	private boolean isCelebrity(String name, List<PersonDTO> peopleList) {
		
		log.info("getCelebrity");
		
		log.debug("Posible celebridad [" + name + "]");
		log.debug("Lista de personas " + peopleList);
		
		
		if (name != null && !name.isEmpty()) {
			
			for (PersonDTO personDTO : peopleList) {
				
				log.debug("Persona " + personDTO);
				
				if(name.equals(personDTO.getName()) && personDTO.getKnownPeopleList()== null) {
					return true;
				} 
				
				if(personDTO.getKnownPeopleList()!= null && 
						personDTO.getKnownPeopleList().contains(name)) {
					log.debug("Lo reconoce como celebridad");
				} else {
					log.error("Todos conocen a las celebridades");
					return false;
				}
				
			}
			
			log.info("Todos lo conocen");
			
			return true;
			
		} else {
			log.error("[" + name + "] no es una celebridad");
			return false;
		}
		
	}

	
	
}
