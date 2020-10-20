package bpdts;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BpdtsService {
	
	private static final Logger LOG = LoggerFactory.getLogger(BpdtsService.class);
	
	private WebClient webClient;
	
	@Value("${bpdts.baseUrl}") 
	private String bpdtsBaseUrl;
	
	@PostConstruct
    private void postConstruct() {
		this.webClient = WebClient.builder().baseUrl(bpdtsBaseUrl).build();
		LOG.info("Base URL: "+bpdtsBaseUrl);
    }
	
	public List<User> getAllUsers() {
		return this.webClient.get().uri("/users").retrieve().bodyToFlux(User.class).collectList().block();
	}
	
	public List<User> getCityListedUsers(String city) {
		return this.webClient.get().uri("/city/{city}/users",city).retrieve().bodyToFlux(User.class).collectList().block();
	}
	
	public List<User> getUsersWithinRadius(double lat, double lng, double radMiles) {
		List<User> allUsers = getAllUsers();

		List<User> usersWithinRadius = allUsers.stream().filter(u -> u.isWithinRadius(lat, lng, radMiles))
				.collect(Collectors.toList());

		return usersWithinRadius;
	}
}
