package bpdts.service;

import static bpdts.utils.UserRadiusPredicate.isWithinRadius;

import java.util.List;
import java.util.stream.Collectors;

import bpdts.model.User;

public class UserServiceImpl implements UserService {
	
	private BpdtsRestAccessor bpdtsRestAccessor;
	
	public UserServiceImpl(BpdtsRestAccessor bpdtsRestAccessor) {
		this.bpdtsRestAccessor=bpdtsRestAccessor;
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
