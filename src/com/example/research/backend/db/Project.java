package com.example.research.backend.db;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Project entity in the JPA database. Stores Project information as defined
 * by the Project's Researcher. Project has a M-1 relationship with the Researcher entity,
 * and a 1-M relationship with the User entity.  */
@Entity
public class Project {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int p_Id;
	
	@NotNull
	@Size(min = 1, max = 49, message = "Must be between 1-49 characters")
	private String name;
	
	private Date start;
	
	@Future
	private Date end;

	@ManyToOne
	private Researcher researcher;
	
	@OneToMany(mappedBy="project")
	private Set<User> users;
	
	private boolean speed, accuracy, longitude, latitude, heading;
	
	private String projectInfo;
	
	/**
	 * Constructor that sets all visible test parameters to false.
	 * @param name Project name.
	 * @param start Project Start time.
	 * @param end Project End time.  */
	public Project(String name, Date start, Date end){
		this.name=name;
		this.start=start;
		this.end=end;
		this.users = new HashSet<User>();
		speed = false;
		accuracy = false;
		longitude = false;
		latitude = false;
		heading = false;
	}
	

	/** Constructor to set all visible test parameters.
	 * @param name Project name.
	 * @param start Project start time.
	 * @param end Project end time.
	 * @param speed Display speed data.
	 * @param accuracy Display accuracy data.
	 * @param longitude Display longitude data.
	 * @param latitude Display latitude data.
	 * @param heading Display heading data.
	 * @param projectInfo Project description.
	 */
	public Project(String name, Date start, Date end, boolean speed,
			boolean accuracy, boolean longitude, boolean latitude, boolean heading, String projectInfo) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.users = new HashSet<User>();
		this.speed = speed;
		this.accuracy = accuracy;
		this.longitude = longitude;
		this.latitude = latitude;
		this.heading = heading;
		this.projectInfo = projectInfo;
	}

	/**
	 * Empty constructor required for JPA
	 */
	public Project(){}

	/**
	 * Gets the name of the project
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the project
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the start date of the project
	 * @return
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * Sets the start date of the project
	 * @param start
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * Gets the end date of the project
	 * @return
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * Sets the end date of the project
	 * @param end
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * Gets the researcher associated with the project
	 * @return
	 */
	public Researcher getResearcher() {
		return researcher;
	}

	/**
	 * Sets the researcher associated with the project
	 * @param researcher
	 */
	public void setResearcher(Researcher researcher) {
		this.researcher = researcher;
	}

	/**
	 * Adds a user to the project
	 * @param user
	 */
	public void addUser(User user) {
		this.users.add(user);
		user.setProject(this);
	}
	
	/**
	 * Gets all users associated with the project
	 * @return
	 */
	public Set<User> getUsers(){
		return users;
	}
	
	/**
	 * Sets the boolean value speed
	 * @param speed
	 */
	public void setSpeed(boolean speed){
		this.speed = speed;
	}
	
	/**
	 * Sets the boolean value accuracy
	 * @param accuracy
	 */
	public void setAccuracy(boolean accuracy){
		this.accuracy = accuracy;
	}
	
	/**
	 * Sets the boolean value longitude
	 * @param longitude
	 */
	public void setLongitude(boolean longitude){
		this.longitude = longitude;
	}
	
	/**
	 * Sets the boolean value latitude
	 * @param latitude
	 */
	public void setLatitude(boolean latitude){
		this.latitude = latitude;
	}
	
	/**
	 * Sets the boolean value heading
	 * @param heading
	 */
	public void setHeading(boolean heading){
		this.heading = heading;
	}
	
	/**
	 * Gets the boolean value of speed
	 * @return
	 */
	public boolean isSpeed(){
		return speed;
	}
	
	/**
	 * Gets the boolean value of accuracy
	 * @return
	 */
	public boolean isAccuracy(){
		return accuracy;
	}
	
	/**
	 * Gets the boolean value of longitude
	 * @return
	 */
	public boolean isLongitude(){
		return longitude;
	}
	
	/**
	 * Gets the boolean value of latitude
	 * @return
	 */
	public boolean isLatitude(){
		return latitude;
	}
	
	/**
	 * Gets the boolean value of heading
	 * @return
	 */
	public boolean isHeading(){
		return heading;
	}
	
	/**
	 * Sets the Project description.
	 * @param heading
	 */
	public void setProjectInfo(String projectInfo){
		this.projectInfo = projectInfo;
	}
	
	/**
	 * Gets the Project description.
	 * @return
	 */
	public String getProjectInfo(){
		return projectInfo;
	}
}
