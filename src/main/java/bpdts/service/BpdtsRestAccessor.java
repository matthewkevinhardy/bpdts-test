package bpdts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import bpdts.model.User;

public class BpdtsRestAccessor {
	
	private static final Logger LOG = LoggerFactory.getLogger(BpdtsRestAccessor.class);
	
	private WebClient webClient;
	
	public BpdtsRestAccessor(String bpdtsBaseUrl) {
		this.webClient = WebClient.builder().baseUrl(bpdtsBaseUrl).build();
		LOG.info("Base URL: "+bpdtsBaseUrl);
	}
	
	public List<User> getAllUsers() {
		return this.webClient.get().uri("/users").retrieve().bodyToFlux(User.class).collectList().block();
	}
	
	public List<User> getCityListedUsers(String city) {
		return this.webClient.get().uri("/city/{city}/users",city).retrieve().bodyToFlux(User.class).collectList().block();
	}
}
