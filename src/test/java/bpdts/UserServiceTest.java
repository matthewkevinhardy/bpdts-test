package bpdts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import bpdts.model.User;
import bpdts.service.BpdtsRestAccessorImpl;
import bpdts.service.UserServiceImpl;

@SpringBootTest
class UserServiceTest {
	
	@Test
	void testGetUsersWithinRadius() {
		BpdtsRestAccessorImpl restAccessorMock = mock(BpdtsRestAccessorImpl.class);
		when(restAccessorMock.getAllUsers()).thenReturn(getMockUserList());
		
		UserServiceImpl userService = new UserServiceImpl(restAccessorMock);
		
		assertThat(userService.getUsersWithinRadius(1, 1, 10)).contains(getMockUserList().get(0));
	}
	
	private List<User> getMockUserList() {
		List<User> mockUserList = new ArrayList<User>();
		mockUserList.add(new User(1,"firstName1","lastName1","email1","ipAddress1",1,1));
		mockUserList.add(new User(2,"firstName2","lastName2","email2","ipAddress2",2,2));
		mockUserList.add(new User(3,"firstName3","lastName3","email3","ipAddress3",3,3));
		return mockUserList;
	}
}
