package bpdts.service;

import static bpdts.utils.UserRadiusPredicate.isWithinRadius;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import bpdts.exception.ResourceNotFoundException;
import bpdts.model.User;

public class UserServiceImpl implements UserService {
	
	private BpdtsRestAccessor bpdtsRestAccessor;
	
	public UserServiceImpl(BpdtsRestAccessor bpdtsRestAccessor) {
		this.bpdtsRestAccessor=bpdtsRestAccessor;
	}
	
	public List<User> getUsersWithinRadius(double lat, double lng, double radMiles) {
		
		try {
			List<User> allUsers = bpdtsRestAccessor.getAllUsers();
			
			return allUsers.stream().filter(isWithinRadius(lat, lng, radMiles))
					.collect(Collectors.toList());
			
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Users Not Found", e);
		}
	}
	
	public List<User> getAllUsers() {
		try {
			return bpdtsRestAccessor.getAllUsers();
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Users Not Found", e);
		}
	}
	
	public List<User> getCityListedUsers(String city) {
		try {
			return bpdtsRestAccessor.getCityListedUsers(city);
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Users Not Found", e);
		}
	}
}
