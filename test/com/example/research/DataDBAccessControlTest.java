package com.example.research;

import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Test;

import com.example.research.backend.DataDBAccessControl;
import com.example.research.backend.db.Data;
import com.example.research.backend.db.testData.DummyDBValues;
import com.vaadin.addon.jpacontainer.JPAContainer;

/** Test for Data Entity Business Logic and JPA access. */
public class DataDBAccessControlTest {

	private static DataDBAccessControl testAccess;
	private static JPAContainer<Data> testContainer;
	
	/**
	 * initialize the dummy data
	 */
	@BeforeClass
	public static void Init(){
		DummyDBValues.createData();
		testAccess = new DataDBAccessControl();
		testContainer = testAccess.getDataContainer();
	}
	
	@Test
	//Unit Test 015: Confirm JPAContainer<Data> initialization.
	public void containerInitTest() {
		assertTrue(testContainer != null);
	}
	
	@Test
	//Unit Test 020: Confirm JPAContainer<Data> Filtering when calling getUserDataContainer().
	public void userDataContainerTest() {
		
		testAccess.reset();
		testAccess.getUserDataContainer("mons02");
		assertTrue(testContainer.size() > 0);
		
	}
	
	@Test
	//Unit Test 022: Confirm JPAContainer<Data> Filtering when calling getProjectDataContainer().
	public void projectDataContainerTest() {
	
		testAccess.reset();
		
		testAccess.getProjectDataContainer(1);
		assertTrue(testContainer.size() > 0);
	}
}
