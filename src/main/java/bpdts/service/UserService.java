package bpdts.service;

import java.util.List;

import bpdts.exception.ResourceNotFoundException;
import bpdts.model.User;

public interface UserService {
	public List<User> getUsersWithinRadius(double lat, double lng, double radMiles) throws ResourceNotFoundException;
	
	public List<User> getAllUsers() throws ResourceNotFoundException;
	
	public List<User> getCityListedUsers(String city) throws ResourceNotFoundException;
}
