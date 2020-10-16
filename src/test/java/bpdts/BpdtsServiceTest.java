package bpdts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BpdtsServiceTest {

	@Autowired
	private BpdtsService bpdtsService;

	@Test
	public void contextLoads() throws Exception {
		assertThat(bpdtsService).isNotNull();
	}

	@Test
	public void getAllUsers() throws Exception {
		assertThat(bpdtsService.getAllUsers()).isNotEmpty();
	}

	@Test
	public void getUsersWithinRadius() throws Exception {
		assertThat(bpdtsService.getUsersWithinRadius(BpdtsApplication.LONDON_LATITUDE,
				BpdtsApplication.LONDON_LONGITUDE, 20)).isNotEmpty();
	}
}
