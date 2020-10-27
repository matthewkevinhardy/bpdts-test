package bpdts.service;

import java.util.List;

import bpdts.exception.ResourceNotFoundException;
import bpdts.model.User;

public interface BpdtsRestAccessor {
	public List<User> getAllUsers() throws ResourceNotFoundException;
	
	public List<User> getCityListedUsers(String city) throws ResourceNotFoundException;
}
