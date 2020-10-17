package bpdts;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@SpringBootApplication
public class BpdtsApplication {

	private static final Logger LOG = LoggerFactory.getLogger(BpdtsApplication.class);

	public static final double LONDON_LATITUDE = 51.509865;
	public static final double LONDON_LONGITUDE = -0.118092;
	public static final String LONDON = "London";
	
	public static void main(String[] args) {
		SpringApplication.run(BpdtsApplication.class, args);
	}

	@Autowired
	private BpdtsService bpdtsService;

	@RestController
	@RequestMapping("bpdts-test")
	class BpdtsController {
		
		@ApiOperation(value = "Get all users")
		@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> getAllUsers() {

			return bpdtsService.getAllUsers();
		}

		@ApiOperation(value = "Get users from coordinates")
		@GetMapping(path = "/coords/users", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> getUsersWithinRadius(@RequestParam(value = "lat", required = true) double lat,
				@RequestParam(value = "lng", required = true) double lng,
				@RequestParam(value = "radMiles", required = true) double radMiles) {

			return bpdtsService.getUsersWithinRadius(lat, lng, radMiles);
		}

		@ApiOperation(value = "Get users within a given radius of London")
		@GetMapping(path = "/getUsersWithinLondonRadius", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> getUsersWithinLondon(@RequestParam(value = "radMiles", required = true,defaultValue = "50") double radMiles) {

			return bpdtsService.getUsersWithinRadius(LONDON_LATITUDE, LONDON_LONGITUDE, radMiles);
		}
		
		@ApiOperation(value = "Get users listed in a city")
		@GetMapping(path = "/city/{city}/users", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> getCityListedUsers(@PathVariable(value = "city") String city) {

			return bpdtsService.getCityListedUsers(city);
		}
		
		@ApiOperation(value = "Get user listed in London")
		@GetMapping(path = "/getLondonListedUsers", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> getLondonListedUsers() {

			return bpdtsService.getCityListedUsers(LONDON);
		}
	}

	@RestController
	class RootController {
		@RequestMapping(value = "/")
		public void redirect(HttpServletResponse response) throws IOException {
			response.sendRedirect("/swagger-ui.html");
		}
	}
}
