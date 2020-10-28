package bpdts.service;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import bpdts.exception.ResourceNotFoundException;
import bpdts.model.User;

public class BpdtsRestAccessorImpl implements BpdtsRestAccessor{
	
	private WebClient webClient;
	
	public BpdtsRestAccessorImpl(WebClient webClient) {
		this.webClient=webClient;
	}
	
	public List<User> getAllUsers() {
		List<User> users = this.webClient.get().uri("/users").retrieve().bodyToFlux(User.class).collectList().block();
		
		if(users.isEmpty()) {
			throw new ResourceNotFoundException("Cant find all users");
		}
		
		return users;
	}
	
	public List<User> getCityListedUsers(String city) {
		List<User> users = this.webClient.get().uri("/city/{city}/users",city).retrieve().bodyToFlux(User.class).collectList().block();
		
		if(users.isEmpty()) {
			throw new ResourceNotFoundException("Cant find users for city: "+city);
		}
		
		return users;
	}
}
