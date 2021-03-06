package com.example.research.backend;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.example.research.ResearchUI;
import com.example.research.backend.db.*;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Compare;

/**
 * Business logic for access to the 'Project' table in the JPA Database.  */
public class ProjectDBAccessControl {
	
	private static JPAContainer<Project> projectContainer = JPAContainerFactory.make(
			Project.class, ResearchUI.PERSISTENCE_UNIT);
	
	/**
	 * Reset the JPAContainer by creating a new one
	 * and setting it to itself  */
	public static void reset(){
		projectContainer = JPAContainerFactory.make(
				Project.class, ResearchUI.PERSISTENCE_UNIT);
	}

	/**
	 * Get JPAContainer of Project Entities, which can be assigned to UI elements for display.
	 * @return Project Container.  */
	public static JPAContainer<Project> getProjectContainer() {		
		return projectContainer;
	}
	
	/**
	 * Get JPAContainer of Project Entities, which can be assigned to UI elements for display.
	 * @param Username of the researcher to filter projects
	 * @return Project Container.  */
	public static JPAContainer<Project> getProjectContainer(String username){
		projectContainer.addNestedContainerProperty("researcher.username");
		Filter filter = new Compare.Equal("researcher.username", username);
		projectContainer.addContainerFilter(filter);
		return projectContainer;
	}
	
	/**
	 * For a given participant, check that the project the participant is enrolled in is currently active.
	 * @param username User name of queried user.
	 * @param current Date value, to be compared to the project's Start and End date.
	 * @return True if current date is within the project's active date range.  */
	public static boolean isProjectOn(String username, Date current){
		EntityManager em = Persistence.createEntityManagerFactory(ResearchUI.PERSISTENCE_UNIT).createEntityManager();
		List queryEnd = em.createQuery("SELECT p.end "
				+ "FROM Project p "
				+ "JOIN User u ON u.project=p "
				+ "WHERE u.username LIKE :username")
				.setParameter("username", username)
				.setMaxResults(1).getResultList();
		
		List queryStart = em.createQuery("SELECT p.start "
				+ "FROM Project p "
				+ "JOIN User u ON u.project=p "
				+ "WHERE u.username LIKE :username")
				.setParameter("username", username)
				.setMaxResults(1).getResultList();
		Date start, end;
		start = (Date) queryStart.get(0);	
		end = (Date) queryEnd.get(0);
		return (current.getTime() < end.getTime() && current.getTime() > start.getTime());
	}
	
	/**
	 * For a given project ID#, check that the corresponding project is currently active.
	 * @param project ID# of queried project.
	 * @param current Date value, to be compared to the project's Start and End date.
	 * @return True if current date is within the project's active date range.  */
	public static boolean isProjectOn(int project, Date current){
		Project p = getProject(project);
		return (current.getTime() < p.getEnd().getTime() && current.getTime() > p.getStart().getTime());
	}
	
	/**
	 * Get Project entity with matching p_Id.
	 * @param p_id ID# of project to find.
	 * @return Corresponding project.  */
	public static Project getProject(int p_id){
		EntityManager em = Persistence.createEntityManagerFactory(ResearchUI.PERSISTENCE_UNIT).createEntityManager();
		List query = em.createQuery("SELECT p "
				+ "FROM Project p "
				+ "WHERE p.p_Id LIKE :p_id")
				.setParameter("p_id", p_id)
				.setMaxResults(1).getResultList();
		if (!query.isEmpty())
			return (Project) query.get(0);
		else
			return null;
	}
}
