package bpdts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BpdtsService {

	private final WebClient webClient;

	public BpdtsService() {
		this.webClient = WebClient.builder().baseUrl("https://bpdts-test-app.herokuapp.com").build();
	}

	public List<User> getAllUsers() {
		return this.webClient.get().uri("/users").retrieve().bodyToFlux(User.class).collectList().block();
	}

	public List<User> getUsersWithinRadius(double lat, double lng, double radMiles) {
		List<User> allUsers = getAllUsers();

		List<User> usersWithinRadius = allUsers.stream().filter(u -> u.isWithinRadius(lat, lng, radMiles))
				.collect(Collectors.toList());

		return usersWithinRadius;
	}
	
//	private static class RadiusPredicate implements Predicate<User>  {
//
//		@Override
//		public boolean test(User t) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//		
//	}
}
