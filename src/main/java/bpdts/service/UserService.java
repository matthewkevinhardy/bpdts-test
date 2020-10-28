package bpdts.service;

import java.util.List;

import bpdts.model.User;

public interface UserService {
	public List<User> getUsersWithinRadius(double lat, double lng, double radMiles);
	
	public List<User> getAllUsers();
	
	public List<User> getCityListedUsers(String city);
}
