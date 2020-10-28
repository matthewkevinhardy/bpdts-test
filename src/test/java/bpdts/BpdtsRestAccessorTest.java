package bpdts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bpdts.exception.ResourceNotFoundException;
import bpdts.service.BpdtsRestAccessorImpl;

@SpringBootTest
public class BpdtsRestAccessorTest {
	
	//private final MockWebServer mockWebServer = new MockWebServer();
	
	@Autowired
	private BpdtsRestAccessorImpl bpdtsRestAccessor;

	@Test
	public void contextLoads() throws ResourceNotFoundException {
		assertThat(bpdtsRestAccessor).isNotNull();
	}

	@Test
	public void getAllUsers() throws ResourceNotFoundException {
		assertThat(bpdtsRestAccessor.getAllUsers()).isNotEmpty();
	}

	@Test
	public void getCityListedUsers() throws ResourceNotFoundException {
		assertThat(bpdtsRestAccessor.getCityListedUsers("London")).isNotEmpty();
	}
}
