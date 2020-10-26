package bpdts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bpdts.service.BpdtsRestAccessor;

@SpringBootTest
public class BpdtsRestAccessorTest {

	@Autowired
	private BpdtsRestAccessor bpdtsRestAccessor;

	@Test
	public void contextLoads() throws Exception {
		assertThat(bpdtsRestAccessor).isNotNull();
	}

	@Test
	public void getAllUsers() throws Exception {
		assertThat(bpdtsRestAccessor.getAllUsers()).isNotEmpty();
	}

	@Test
	public void getCityListedUsers() throws Exception {
		assertThat(bpdtsRestAccessor.getCityListedUsers("London")).isNotEmpty();
	}
}
