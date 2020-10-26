package bpdts.service;

import java.util.List;
import java.util.function.Predicate;

import bpdts.model.User;

public interface UserService {
	public boolean isWithinRadius(User user,double lat, double lng, double maxRadiusMiles);
	
	public Predicate<User> isWithinRadius(double lat, double lng, double radMiles);
	
	public List<User> getUsersWithinRadius(double lat, double lng, double radMiles);
	
	public List<User> getAllUsers();
	
	public List<User> getCityListedUsers(String city);
}
