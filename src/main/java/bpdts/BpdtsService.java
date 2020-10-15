package bpdts;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BpdtsService {
	
	private final WebClient webClient;
	
	public BpdtsService() {
		this.webClient = WebClient.builder().baseUrl("https://bpdts-test-app.herokuapp.com").build();
	}

	public List<User> getAllUsers() {
		return this.webClient.get().uri("/users")
						.retrieve().bodyToFlux(User.class).collectList().block();
	}

}
