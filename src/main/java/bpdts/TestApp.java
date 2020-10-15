package bpdts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class TestApp {

	private static final Logger LOG = LoggerFactory.getLogger(TestApp.class);

	private static final double LONDON_LATITUDE = 51.5074;
	private static final double LONDON_LONGITUDE = 0.1278;
	private static final double DEGREES_TO_MILES = 69.2;

	public static void main(String[] args) {
		SpringApplication.run(TestApp.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private BpdtsService bpdtsService;

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			List<User> users = bpdtsService.getAllUsers();
			LOG.info(users.get(0).toString());
		};
	}

	@RestController
	@RequestMapping("bpdts-test")
	class BpdtsController {

		@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
		public Map<String, Object> loginController(@RequestBody Map<String, String> data) {

			bpdtsService.getAllUsers();

			return null;
		}
	}
}
