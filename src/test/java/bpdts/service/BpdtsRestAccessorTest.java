package bpdts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bpdts.model.User;

@SpringBootTest
class BpdtsRestAccessorTest {

	@Autowired
	private BpdtsRestAccessor bpdtsRestAccessor;
	
	@Test
	void testGetAllUsers() {
		assertThat(bpdtsRestAccessor.getAllUsers()).hasSize(1000);
	}

	@Test
	void testGetCityListedUsers() {
		List<User> londonUsers = bpdtsRestAccessor.getCityListedUsers("London");
		
		assertThat(londonUsers).hasSize(6);
		
		MatcherAssert.assertThat(londonUsers, hasItems(
				hasProperty("id",equalTo(135L)),
				hasProperty("id",equalTo(396L)),
				hasProperty("id",equalTo(520L)),
				hasProperty("id",equalTo(658L)),
				hasProperty("id",equalTo(688L)),
				hasProperty("id",equalTo(794L))
				));
	}
}
