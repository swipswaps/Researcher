package com.example.research.backend;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.example.research.ResearchUI;
import com.example.research.backend.db.Data;

/** Default Control class for MapView that maintains current location of user. */
public class MapControl {
	
	private double longitude;
	private double latitude;
	
	public MapControl() {}
	
	/** Get User's Longitude.
	 * @return Longitude of User.
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/** Set User's Longitude.
	 * @param longitude New Longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/** Get User's Latitude.
	 * @return Latitude of User.
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/** Set User's Latitude.
	 * @param longitude New Latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public List<Data> getMapPoints(String username){
		EntityManager em = Persistence.createEntityManagerFactory(ResearchUI.PERSISTENCE_UNIT).createEntityManager();
		@SuppressWarnings("unchecked")
		List<Data> points = em.createQuery("SELECT d "
				+ "FROM Data d "
				+ "JOIN User u ON d.user=u "
				+ "WHERE u.username LIKE :username")
				.setParameter("username", username)
				.getResultList();
		
		return points;
	}
}