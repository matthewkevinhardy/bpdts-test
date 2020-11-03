package bpdts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bpdts.model.User;

class UserServiceTest {

	static BpdtsRestAccessor restAccessorMock = null;
	static UserService userService = null;
	
	@BeforeAll
	static void beforeAll() {
		restAccessorMock = mock(BpdtsRestAccessorImpl.class);
		when(restAccessorMock.getAllUsers()).thenReturn(getMockUserList());
		
		userService = new UserServiceImpl(restAccessorMock);
	}
	
	@Test
	void testGetUsersWithinRadius() {
		List<User> usersWithin60Miles = userService.getUsersWithinRadius(1, 1, 60);
		List<User> usersWithin140Miles = userService.getUsersWithinRadius(1, 1, 140);
		List<User> usersWithin200Miles = userService.getUsersWithinRadius(1, 1, 200);
		
		assertThat(usersWithin60Miles).containsExactly(getMockUserList().get(0));
		assertThat(usersWithin140Miles).containsExactly(getMockUserList().get(0),getMockUserList().get(1));
		assertThat(usersWithin200Miles).containsExactly(getMockUserList().get(0),getMockUserList().get(1),getMockUserList().get(2));
		
		assertThat(userService.getUsersWithinRadius(1, 1, 10)).containsExactly(getMockUserList().get(0));
	}

	@Test
	void testGetAllUsers() {
		assertThat(userService.getAllUsers()).containsAll(getMockUserList());
	}

	static List<User> getMockUserList() {
		List<User> mockUserList = new ArrayList<User>();
		mockUserList.add(new User(1,"firstName1","lastName1","email1","ipAddress1",1,1));
		mockUserList.add(new User(2,"firstName2","lastName2","email2","ipAddress2",2,2));
		mockUserList.add(new User(3,"firstName3","lastName3","email3","ipAddress3",3,3));
		mockUserList.add(new User(4,"firstName4","lastName4","email4","ipAddress4",4,4));
		return mockUserList;
	}
}
