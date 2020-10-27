package bpdts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bpdts.exception.ResourceNotFoundException;
import bpdts.model.User;
import bpdts.service.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("bpdts-test")
public class BpdtsController {

	@Value("${bpdts.londonLatitude}")
	private double londonLatitude;

	@Value("${bpdts.londonLongitude}")
	private double londonLongitude;

	@Value("${bpdts.london}")
	private String londonName;

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Get all users")
	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {

		try {
			return userService.getAllUsers();
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users Not Found", e);
		}
	}

	@ApiOperation(value = "Get users from coordinates")
	@GetMapping(path = "/coords/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsersWithinRadius(@RequestParam(value = "lat", required = true) double lat,
			@RequestParam(value = "lng", required = true) double lng,
			@RequestParam(value = "radMiles", required = true) double radMiles) {

		try {
			return userService.getUsersWithinRadius(lat, lng, radMiles);
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users Not Found", e);
		}
	}

	@ApiOperation(value = "Get users within a given radius of London")
	@GetMapping(path = "/getUsersWithinLondonRadius", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsersWithinLondon(
			@RequestParam(value = "radMiles", required = true, defaultValue = "50") double radMiles) {

		try {
			return userService.getUsersWithinRadius(londonLatitude, londonLongitude, radMiles);
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users Not Found", e);
		}
	}

	@ApiOperation(value = "Get users listed in a city")
	@GetMapping(path = "/city/{city}/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getCityListedUsers(@PathVariable(value = "city") String city) {

		try {
			return userService.getCityListedUsers(city);
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users Not Found", e);
		}
	}

	@ApiOperation(value = "Get user listed in London")
	@GetMapping(path = "/getLondonListedUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getLondonListedUsers() {

		try {
			return userService.getCityListedUsers(londonName);
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users Not Found", e);
		}
	}
}
