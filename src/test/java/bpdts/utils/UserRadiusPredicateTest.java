package bpdts.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import bpdts.model.User;

class UserRadiusPredicateTest {

	@Test
	void testIsWithinRadius() {
		assertTrue(UserRadiusPredicate.isWithinRadius(getMockUserList().get(0), 1, 1, 1));
	}

	@Test
	void testIsWithinRadiusDoubleDoubleDouble() {
		List<User> usersWithinRadius = getMockUserList().stream()
				.filter(UserRadiusPredicate.isWithinRadius(1, 1, 10))
				.collect(Collectors.toList());

		assertThat(usersWithinRadius).contains(getMockUserList().get(0));
	}

	private List<User> getMockUserList() {
		List<User> mockUserList = new ArrayList<User>();
		mockUserList.add(new User(1, "firstName1", "lastName1", "email1", "ipAddress1", 1, 1));
		mockUserList.add(new User(2, "firstName2", "lastName2", "email2", "ipAddress2", 2, 2));
		mockUserList.add(new User(3, "firstName3", "lastName3", "email3", "ipAddress3", 3, 3));
		return mockUserList;
	}
}
