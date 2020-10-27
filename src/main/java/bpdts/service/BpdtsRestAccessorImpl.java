	package bpdts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.WebClient;

import bpdts.exception.ResourceNotFoundException;
import bpdts.model.User;

public class BpdtsRestAccessorImpl implements BpdtsRestAccessor{
	
	private static final Logger LOG = LoggerFactory.getLogger(BpdtsRestAccessorImpl.class);
	
	private WebClient webClient;
	
	public BpdtsRestAccessorImpl(String bpdtsBaseUrl) {
		this.webClient = WebClient.builder().baseUrl(bpdtsBaseUrl).build();
		LOG.info("Base URL: "+bpdtsBaseUrl);
	}
	
	public List<User> getAllUsers() throws ResourceNotFoundException {
		List<User> users = this.webClient.get().uri("/users").retrieve().bodyToFlux(User.class).collectList().block();
		
		if(users.isEmpty()) {
			throw new ResourceNotFoundException("Cant find all users");
		}
		
		return users;
	}
	
	public List<User> getCityListedUsers(String city) throws ResourceNotFoundException {
		List<User> users = this.webClient.get().uri("/city/{city}/users",city).retrieve().bodyToFlux(User.class).collectList().block();
		
		if(users.isEmpty()) {
			throw new ResourceNotFoundException("Cant find users for city: "+city);
		}
		
		return users;
	}
}
