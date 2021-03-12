package com.mx.eon.celebritymvn.test.bo;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.mx.eon.celebritymvn.constants.CelebrityConstants.MSG_ERROR_NO_CELEBRITIES;
import static com.mx.eon.celebritymvn.constants.CelebrityConstants.MSG_ERROR_LIST_EMPTY_OR_NULL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.eon.celebritymvn.bo.CelebrityBO;
import com.mx.eon.celebritymvn.dto.PersonDTO;

import lombok.extern.log4j.Log4j;

/**
 * Clase para probar {@link CelebrityBO}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@SpringBootTest
class CelebrityBOTest {
	
	
	/**
	 * {@link CelebrityBO}
	 */
	@Autowired
	private CelebrityBO celebrityBO;
	
	
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
	 * metodo para probar dividePeople
	 */
	@Test
	void testDividePeople() {
		
		log.info("testDividePeople");
		
		/*Test 1*/
		List<PersonDTO> peopleList = null;
		
		Map<Boolean, List<PersonDTO>> peopleMap = celebrityBO.dividePeople(peopleList);
		
		log.info("Mapa de division de personas " + peopleMap);
		
		assertNull(peopleMap);
		
		
		/*Test 2*/
		peopleList = new ArrayList<>();
		
		peopleMap = celebrityBO.dividePeople(peopleList);
		
		log.info("Mapa de division de personas " + peopleMap);
		
		assertNull(peopleMap);
		
		
		/*Test 3*/
		peopleList = new ArrayList<>();
		
		peopleList.add(celebrity1);
		peopleList.add(celebrity2);
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		log.info("Lista de personas " + peopleList);
		
		peopleMap = celebrityBO.dividePeople(peopleList);
		
		log.info("Mapa de division de personas " + peopleMap);
		
		assertNotNull(peopleMap);
		
		log.info("celebridades " + peopleMap.get(true));
		assertTrue(peopleMap.get(true) != null && !peopleMap.get(true).isEmpty());
		
		log.info("normales " + peopleMap.get(false));
		assertTrue(peopleMap.get(false) != null && !peopleMap.get(false).isEmpty());
		
		
		/*Test 4*/
		peopleList = new ArrayList<>();
		
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		log.info("Lista de personas " + peopleList);
		
		peopleMap = celebrityBO.dividePeople(peopleList);
		
		log.info("Mapa de division de personas " + peopleMap);
		
		assertNotNull(peopleMap);
		
		log.info("celebridades " + peopleMap.get(true));
		assertTrue(peopleMap.get(true).isEmpty());
		
		log.info("normales " + peopleMap.get(false));
		assertTrue(peopleMap.get(false) != null && !peopleMap.get(false).isEmpty());
		
		
		/*Test 5*/
		peopleList = new ArrayList<>();
		
		peopleList.add(celebrity1);
		peopleList.add(celebrity2);

		
		log.info("Lista de personas " + peopleList);
		
		peopleMap = celebrityBO.dividePeople(peopleList);
		
		log.info("Mapa de division de personas " + peopleMap);
		
		assertNotNull(peopleMap);
		
		log.info("celebridades " + peopleMap.get(true));
		assertTrue(peopleMap.get(true) != null && !peopleMap.get(true).isEmpty());
		
		log.info("normales " + peopleMap.get(false));
		assertTrue(peopleMap.get(false).isEmpty());
		
	}

	
	/**
	 * metodo para probar extractCelebrities
	 */
	@Test
	void testExtractCelebrities() {
		
		log.info("testExtractCelebrities");
		
		/*Test 1*/
		List<PersonDTO> possibleCelebList = null; 
		List<PersonDTO> peopleList = null;
		
		List<PersonDTO> celebList = celebrityBO.extractCelebrities(possibleCelebList, peopleList);
		
		log.info("Lista de celebridades generada " + celebList);
		
		assertNull(celebList);
		
		
		/*Test 2*/
		possibleCelebList = new ArrayList<>(); 
		peopleList = null;
		
		celebList = celebrityBO.extractCelebrities(possibleCelebList, peopleList);
		
		log.info("Lista de celebridades generada " + celebList);
		
		assertNull(celebList);
		
		
		/*Test 3*/
		possibleCelebList = null; 
		peopleList = new ArrayList<>();
		
		celebList = celebrityBO.extractCelebrities(possibleCelebList, peopleList);
		
		log.info("Lista de celebridades generada " + celebList);
		
		assertNull(celebList);
		
		
		/*Test 4*/
		possibleCelebList = new ArrayList<>(); 
		peopleList = new ArrayList<>();
		
		celebList = celebrityBO.extractCelebrities(possibleCelebList, peopleList);
		
		log.info("Lista de celebridades generada " + celebList);
		
		assertNull(celebList);
		
		/*Test 5*/
		possibleCelebList = new ArrayList<>(); 
		possibleCelebList.add(celebrity1);
		possibleCelebList.add(celebrity2);
		
		peopleList = new ArrayList<>();
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		celebList = celebrityBO.extractCelebrities(possibleCelebList, peopleList);
		
		log.info("Lista de celebridades generada " + celebList);
		
		assertNotNull(celebList);
		
		assertTrue(!celebList.isEmpty() && celebList.size() == 2);
		
		/*Test 6*/
		possibleCelebList = new ArrayList<>(); 
		possibleCelebList.add(new PersonDTO("Tom", null));
		
		
		peopleList = new ArrayList<>();
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		celebList = celebrityBO.extractCelebrities(possibleCelebList, peopleList);
		
		log.info("Lista de celebridades generada " + celebList);
		
		assertNotNull(celebList);
		
		
		/*Test 7*/
		possibleCelebList = new ArrayList<>(); 
		possibleCelebList.add(celebrity1);
		possibleCelebList.add(celebrity2);
		
		peopleList = new ArrayList<>();
		peopleList.add(celebrity1);
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		celebList = celebrityBO.extractCelebrities(possibleCelebList, peopleList);
		
		log.info("Lista de celebridades generada " + celebList);
		
		assertNotNull(celebList);
		
		/*Test 8*/
		possibleCelebList = new ArrayList<>(); 
		possibleCelebList.add(celebrity1);
		possibleCelebList.add(celebrity2);
		possibleCelebList.add(new PersonDTO(null, null));
		possibleCelebList.add(new PersonDTO("", null));
		
		peopleList = new ArrayList<>();
		peopleList.add(new PersonDTO(null, null));
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		celebList = celebrityBO.extractCelebrities(possibleCelebList, peopleList);
		
		log.info("Lista de celebridades generada " + celebList);
		
		assertNotNull(celebList);
		
	}
	
	/**
	 * Metodo para probar getCelebritiesStr
	 */
	@Test
	void testGetCelebritiesStr() {
		
		log.info("testGetCelebritiesStr");
		
		/*Test 1*/
		List<PersonDTO> celebList = null;
		
		String celebrities = celebrityBO.getCelebritiesStr(celebList);
		
		log.info("String generado [" + celebrities + "]");
		
		assertNull(celebrities);
		
		
		/*Test 2*/
		celebList = new ArrayList<>();
		
		celebrities = celebrityBO.getCelebritiesStr(celebList);
		
		log.info("String generado [" + celebrities + "]");
		
		assertNull(celebrities);
		
		
		/*Test 3*/
		celebList = new ArrayList<>();
		celebList.add(celebrity1);
		celebList.add(celebrity2);
		
		celebrities = celebrityBO.getCelebritiesStr(celebList);
		
		log.info("String generado [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		/*Test 4*/
		celebList = new ArrayList<>();
		celebList.add(new PersonDTO(null, null));
		celebList.add(new PersonDTO("", null));
		
		celebrities = celebrityBO.getCelebritiesStr(celebList);
		
		log.info("String generado [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
	}
	
	/**
	 * metodo para probar findCelebrities
	 */
	@Test
	void testFindCelebrities() {
		
		log.info("testFindCelebrities");
		
		/*Test 1*/
		List<PersonDTO> peopleList = null;
		
		String celebrities = celebrityBO.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		assertEquals(MSG_ERROR_LIST_EMPTY_OR_NULL, celebrities);
		
		
		/*Test 2*/
		peopleList = new ArrayList<>();
		
		celebrities = celebrityBO.findCelebrities(peopleList);
		
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
		
		celebrities = celebrityBO.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		/*Test 4*/
		peopleList = new ArrayList<>();
		
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		log.info("Lista de personas " + peopleList);
		
		celebrities = celebrityBO.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		assertEquals(MSG_ERROR_NO_CELEBRITIES, celebrities);
		
		
		/*Test 5 (no hay publico)*/
		peopleList = new ArrayList<>();
		
		peopleList.add(celebrity1);
		peopleList.add(celebrity2);
		
		log.info("Lista de personas " + peopleList);
		
		celebrities = celebrityBO.findCelebrities(peopleList);
		
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
		
		celebrities = celebrityBO.findCelebrities(peopleList);
		
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
		
		PersonDTO tom = new PersonDTO();
		
		tom.setName("Tom");
		tom.setKnownPeopleList(knownPeopleList);
		
		peopleList.add(tom);
		
		log.info("Lista de personas " + peopleList);
		
		celebrities = celebrityBO.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		assertEquals(MSG_ERROR_NO_CELEBRITIES, celebrities);
		
		
		/*Test 8*/
		peopleList = new ArrayList<>();
		
		peopleList.add(new PersonDTO(null, null));
		peopleList.add(celebrity2);
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		peopleList.add(tom);
		
		log.info("Lista de personas " + peopleList);
		
		celebrities = celebrityBO.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		assertEquals(MSG_ERROR_NO_CELEBRITIES, celebrities);
		
		/*Test 9*/
		peopleList = new ArrayList<>();
		
		peopleList.add(new PersonDTO("", null));
		peopleList.add(celebrity2);
		peopleList.add(person1);
		peopleList.add(person2);
		peopleList.add(person3);
		
		peopleList.add(tom);
		
		log.info("Lista de personas " + peopleList);
		
		celebrities = celebrityBO.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		assertEquals(MSG_ERROR_NO_CELEBRITIES, celebrities);
		
		
		/*Test 10*/
		peopleList = new ArrayList<>();
		
		peopleList.add(new PersonDTO(null, null));
		peopleList.add(new PersonDTO(null, null));
		peopleList.add(new PersonDTO(null, null));

		
		peopleList.add(tom);
		
		log.info("Lista de personas " + peopleList);
		
		celebrities = celebrityBO.findCelebrities(peopleList);
		
		log.info("resultado de la busqueda de celebridades [" + celebrities + "]");
		
		assertNotNull(celebrities);
		
		assertEquals(MSG_ERROR_NO_CELEBRITIES, celebrities);
		
		
		
	}
	
}
