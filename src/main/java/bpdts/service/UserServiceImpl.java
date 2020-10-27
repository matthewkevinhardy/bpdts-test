package bpdts.service;

import static bpdts.utils.UserRadiusPredicate.isWithinRadius;

import java.util.List;
import java.util.stream.Collectors;

import bpdts.exception.ResourceNotFoundException;
import bpdts.model.User;

public class UserServiceImpl implements UserService {

	private BpdtsRestAccessor bpdtsRestAccessor;

	public UserServiceImpl(BpdtsRestAccessor bpdtsRestAccessor) {
		this.bpdtsRestAccessor = bpdtsRestAccessor;
	}

	public List<User> getUsersWithinRadius(double lat, double lng, double radMiles) throws ResourceNotFoundException {

		List<User> allUsers = bpdtsRestAccessor.getAllUsers();

		return allUsers.stream().filter(isWithinRadius(lat, lng, radMiles)).collect(Collectors.toList());
	}

	public List<User> getAllUsers() throws ResourceNotFoundException {
		return bpdtsRestAccessor.getAllUsers();

	}

	public List<User> getCityListedUsers(String city) throws ResourceNotFoundException {
		return bpdtsRestAccessor.getCityListedUsers(city);

	}
}
