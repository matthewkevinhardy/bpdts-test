package bpdts.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import bpdts.model.User;

public class UserService {
	
	private BpdtsRestAccessor bpdtsRestAccessor;
	
	public UserService(BpdtsRestAccessor bpdtsRestAccessor) {
		this.bpdtsRestAccessor=bpdtsRestAccessor;
	}
	
	public static final double DEGREES_TO_MILES = 69.2;
	
	public boolean isWithinRadius(User user,double lat, double lng, double maxRadiusMiles) {
		double x = user.getLatitude()-lat;
		double y = user.getLongitude()-lng;
		double radiusMiles = Math.sqrt(x*x+y*y)*DEGREES_TO_MILES;
		
		if(radiusMiles<=maxRadiusMiles) {
			return true;
		}
		return false;
	}
	
	public Predicate<User> isWithinRadius(double lat, double lng, double radMiles) {
		return u -> isWithinRadius(u, lat, lng, radMiles);
	}
	
	public List<User> getUsersWithinRadius(double lat, double lng, double radMiles) {
		List<User> allUsers = bpdtsRestAccessor.getAllUsers();

		List<User> usersWithinRadius = allUsers.stream().filter(isWithinRadius(lat, lng, radMiles))
				.collect(Collectors.toList());

		return usersWithinRadius;
	}
	
	public List<User> getAllUsers() {
		return bpdtsRestAccessor.getAllUsers();
	}
	
	public List<User> getCityListedUsers(String city) {
		return bpdtsRestAccessor.getCityListedUsers(city);
	}
}
