package bpdts.service;

import java.util.List;

import bpdts.model.User;

public interface BpdtsRestAccessor {
	public List<User> getAllUsers();
	
	public List<User> getCityListedUsers(String city);
}
