package com.example.research;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.example.research.backend.db.Project;

/** Tests for validation of a newly-created project.  */
public class NewProjectValidatorTest {

	private Validator validator;
	private final long oneDay = 86400000;
	private final long twoDays = 2*oneDay;

	/**
	 * Initialize the validator
	 */
    @Before
    public void init() {

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();

    }

    @Test
    //UT023: Test for valid name input
    public void validNameTest() {
        Project p = new Project("name", new Date(System.currentTimeMillis()+oneDay), 
        		new Date(System.currentTimeMillis()+twoDays));
        Set<ConstraintViolation<Project>> violations = this.validator.validate(p);
        assertTrue(violations.isEmpty());
    }
    
    @Test
    //UT024: Test for empty name input
    public void emptyNameTest() {
        Project p = new Project("", new Date(System.currentTimeMillis()+oneDay), 
        		new Date(System.currentTimeMillis()+twoDays));
        Set<ConstraintViolation<Project>> violations = this.validator.validate(p);
        assertFalse(violations.isEmpty());
    }
    
    @Test
    //UT025: Test for name out of range input
    public void nameOutOfRangeTest() {
        Project p = new Project("bbbbbbbbbbbbbbbbbbbbbbbbbb"
        		+ "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", new Date(System.currentTimeMillis()+oneDay), 
        		new Date(System.currentTimeMillis()+twoDays));
        Set<ConstraintViolation<Project>> violations = this.validator.validate(p);
        assertFalse(violations.isEmpty());
    }
    
    @Test
    //UT026: Test for valid date input
    public void validDateRangeTest() {
        Project p = new Project("validStartDate", new Date(System.currentTimeMillis()+oneDay), 
        		new Date(System.currentTimeMillis()+twoDays));
        Set<ConstraintViolation<Project>> violations = this.validator.validate(p);
        assertTrue(violations.isEmpty());
    }
    
    

    
    @Test
    //UT028: Test for invalid end date input
    public void invalidEndDateTest() {
        Project p = new Project("invalidEndDate", new Date(System.currentTimeMillis()+oneDay), 
        		new Date(System.currentTimeMillis()-oneDay));
        Set<ConstraintViolation<Project>> violations = this.validator.validate(p);
        assertFalse(violations.isEmpty());
    }
    
}
