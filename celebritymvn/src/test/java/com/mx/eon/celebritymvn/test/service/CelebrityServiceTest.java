package com.mx.eon.celebritymvn.test.service;

import static com.mx.eon.celebritymvn.constants.CelebrityConstants.MSG_ERROR_LIST_EMPTY_OR_NULL;
import static com.mx.eon.celebritymvn.constants.CelebrityConstants.MSG_ERROR_NO_CELEBRITIES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.eon.celebritymvn.dto.PersonDTO;
import com.mx.eon.celebritymvn.service.CelebrityService;

import lombok.extern.log4j.Log4j;

/**
 * Clase para probar {@link CelebrityService}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@SpringBootTest
class CelebrityServiceTest {

	/**
	 * {@link CelebrityService}
	 */
	@Autowired
	private CelebrityService celebrityService;
	
	/**
	 * persona 1
	 */
	private PersonDTO person1;
	
	/**
	 * persona 2
	 */
	private PersonDTO person2;
	
	/**
	 * persona 3
	 */
	private PersonDTO person3;
	
	/**
	 * Celebridad 1
	 */
	private PersonDTO celebrity1;
	
	/**
	 * Celebridad 2
	 */
	private PersonDTO celebrity2;
	
	
	
	/**
	 * Metodo para inicializar Objetos
	 */
	@BeforeEach
	void init() {
		
		log.info("ini");
		
		person1 = new PersonDTO("John", null);
		person2 = new PersonDTO("Jane", null);
		person3 = new PersonDTO("Jim", null);
		
		
		celebrity1 = new PersonDTO("Kurt", null);
		celebrity2 = new PersonDTO("Maynard", null);
		
		List<String> knownPeopleList = new ArrayList<>();
		
		knownPeopleList.add(celebrity1.getName());
		knownPeopleList.add(celebrity2.getName());
		
		person1 = new PersonDTO("John", knownPeopleList);
		person2 = new PersonDTO("Jane", knownPeopleList);
		person3 = new PersonDTO("Jim", knownPeopleList);
		
	}
	
	/**
	 * metodo para probar findCelebrities
	 */
	@Test
	void testFindCelebrities() {
		
		log.info("testFindCelebrities");
		
		/*Test 1*/
		List<PersonDTO> peopleList = null;
		
		String celebrities = celebrityService.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		assertEquals(MSG_ERROR_LIST_EMPTY_OR_NULL, celebrities);
		
		
		/*Test 2*/
		peopleList = new ArrayList<>();
		
		celebrities = celebrityService.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		assertEquals(MSG_ERROR_LIST_EMPTY_OR_NULL, celebrities);
		
		
		/*Test 3*/
		peopleList = new ArrayList<>();
		
		peopleList.add(celebrity1);
		peopleList.add(celebrity2);
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		log.info("Lista de personas " + peopleList);
		
		celebrities = celebrityService.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		/*Test 4*/
		peopleList = new ArrayList<>();
		
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		log.info("Lista de personas " + peopleList);
		
		celebrities = celebrityService.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		assertEquals(MSG_ERROR_NO_CELEBRITIES, celebrities);
		
		
		/*Test 5 (no hay publico)*/
		peopleList = new ArrayList<>();
		
		peopleList.add(celebrity1);
		peopleList.add(celebrity2);
		
		log.info("Lista de personas " + peopleList);
		
		celebrities = celebrityService.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		assertEquals(MSG_ERROR_NO_CELEBRITIES, celebrities);
		
		
		/*Test 6*/
		peopleList = new ArrayList<>();
		
		peopleList.add(new PersonDTO("Tom", null));
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		log.info("Lista de personas " + peopleList);
		
		celebrities = celebrityService.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		assertEquals(MSG_ERROR_NO_CELEBRITIES, celebrities);
		
		
		/*Test 7*/
		peopleList = new ArrayList<>();
		
		peopleList.add(celebrity1);
		peopleList.add(celebrity2);
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		List<String> knownPeopleList = new ArrayList<>();
		knownPeopleList.add("Pepe");
		knownPeopleList.add("Paco");
		
		PersonDTO tom = new PersonDTO("Tom", knownPeopleList);
		
		assertNotNull(tom.getName());
		assertNotNull(tom.getKnownPeopleList());
		
		peopleList.add(tom);
		
		log.info("Lista de personas " + peopleList);
		
		celebrities = celebrityService.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		assertEquals(MSG_ERROR_NO_CELEBRITIES, celebrities);
		
	}
	
}
