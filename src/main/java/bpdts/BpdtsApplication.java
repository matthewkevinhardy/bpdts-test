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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BpdtsApplication {

	private static final Logger LOG = LoggerFactory.getLogger(BpdtsApplication.class);

	public static final double LONDON_LATITUDE = 51.509865;
	public static final double LONDON_LONGITUDE = -0.118092;

	public static void main(String[] args) {
		SpringApplication.run(BpdtsApplication.class, args);
	}

	@Autowired
	private BpdtsService bpdtsService;

	@RestController
	@RequestMapping("bpdts-test")
	class BpdtsController {

		@GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> getAllUsers() {

			return bpdtsService.getAllUsers();
		}

		@GetMapping(path = "/getUsersWithinRadius", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> getUsersWithinRadius(@RequestParam(value = "lat", required = true) double lat,
				@RequestParam(value = "lng", required = true) double lng,
				@RequestParam(value = "radMiles", required = true) double radMiles) {

			return bpdtsService.getUsersWithinRadius(lat, lng, radMiles);
		}

		@GetMapping(path = "/getUsersWithinLondon", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> getUsersWithinLondon(@RequestParam(value = "radMiles", required = true) double radMiles) {

			return bpdtsService.getUsersWithinRadius(LONDON_LATITUDE, LONDON_LONGITUDE, radMiles);
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