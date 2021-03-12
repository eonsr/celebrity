package com.mx.eon.celebritymvn.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.eon.celebritymvn.bo.CelebrityBO;
import com.mx.eon.celebritymvn.dto.PersonDTO;
import com.mx.eon.celebritymvn.service.CelebrityService;

import lombok.extern.log4j.Log4j;

/**
 * Implementacion de {@link CelebrityService}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@Service
public class CelebrityServiceImp implements CelebrityService {
	
	/**
	 * {@link CelebrityBO}
	 */
	@Autowired
	private CelebrityBO celebrityBO; 

	@Override
	public String findCelebrities(List<PersonDTO> peopleList) {
		
		log.info("findCelebrities");
		
		String celebrities = celebrityBO.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		return celebrities;
	}

}
