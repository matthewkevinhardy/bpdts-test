package bpdts.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import bpdts.model.User;

class UserRadiusPredicateTest {

	@Test
	void testIsWithinRadius() {
		List<User> usersWithin60Miles = getMockUserList().stream()
				.filter(UserRadiusPredicate.isWithinRadius(1, 1, 10))
				.collect(Collectors.toList());
		
		List<User> usersWithin140Miles = getMockUserList().stream()
				.filter(UserRadiusPredicate.isWithinRadius(1, 1, 140))
				.collect(Collectors.toList());
		
		List<User> usersWithin200Miles = getMockUserList().stream()
				.filter(UserRadiusPredicate.isWithinRadius(1, 1, 200))
				.collect(Collectors.toList());
		
		assertThat(usersWithin60Miles).containsExactly(getMockUserList().get(0));
		assertThat(usersWithin140Miles).containsExactly(getMockUserList().get(0),getMockUserList().get(1));
		assertThat(usersWithin200Miles).containsExactly(getMockUserList().get(0),getMockUserList().get(1),getMockUserList().get(2));
	}

	private List<User> getMockUserList() {
		List<User> mockUserList = new ArrayList<User>();
		mockUserList.add(new User(1, "firstName1", "lastName1", "email1", "ipAddress1", 1, 1));
		mockUserList.add(new User(2, "firstName2", "lastName2", "email2", "ipAddress2", 2, 2));
		mockUserList.add(new User(3, "firstName3", "lastName3", "email3", "ipAddress3", 3, 3));
		mockUserList.add(new User(4, "firstName4", "lastName4", "email4", "ipAddress4", 4, 4));
		return mockUserList;
	}
}
