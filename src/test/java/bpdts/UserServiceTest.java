package bpdts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import bpdts.model.User;
import bpdts.service.BpdtsRestAccessor;
import bpdts.service.UserService;

@SpringBootTest
class UserServiceTest {
	
	@Test
	void testIsWithinRadiusPredicate() {
		BpdtsRestAccessor restAccessorMock = mock(BpdtsRestAccessor.class);
		when(restAccessorMock.getAllUsers()).thenReturn(getMockUserList());
		
		UserService userService = new UserService(restAccessorMock);
		
		List<User> usersWithinRadius = getMockUserList().stream().filter(userService.isWithinRadius(1, 1, 10))
				.collect(Collectors.toList());
		
		assertThat(usersWithinRadius).contains(getMockUserList().get(0));
	}

	@Test
	void testIsWithinRadius() {
		BpdtsRestAccessor restAccessorMock = mock(BpdtsRestAccessor.class);
		when(restAccessorMock.getAllUsers()).thenReturn(getMockUserList());
		
		UserService userService = new UserService(restAccessorMock);
		
		assertTrue(userService.isWithinRadius(getMockUserList().get(0), 1, 1, 10));
	}

	@Test
	void testGetUsersWithinRadius() {
		BpdtsRestAccessor restAccessorMock = mock(BpdtsRestAccessor.class);
		when(restAccessorMock.getAllUsers()).thenReturn(getMockUserList());
		
		UserService userService = new UserService(restAccessorMock);
		
		assertThat(userService.getUsersWithinRadius(1, 1, 10)).contains(getMockUserList().get(0));
	}

//	@Test
//	void testGetAllUsers() {
//		assertThat(userService.getAllUsers()).isNotEmpty();
//	}
//
//	@Test
//	void testGetCityListedUsers() {
//		assertThat(userService.getCityListedUsers("London")).isNotEmpty();
//	}
	
	private List<User> getMockUserList() {
		List<User> mockUserList = new ArrayList<User>();
		mockUserList.add(new User(1,"firstName1","lastName1","email1","ipAddress1",1,1));
		mockUserList.add(new User(2,"firstName2","lastName2","email2","ipAddress2",2,2));
		mockUserList.add(new User(3,"firstName3","lastName3","email3","ipAddress3",3,3));
		return mockUserList;
	}
}
