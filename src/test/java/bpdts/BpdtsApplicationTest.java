package bpdts;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class BpdtsApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Value("${bpdts.londonLatitude}")
	private double londonLatitude;

	@Value("${bpdts.londonLongitude}")
	private double londonLongitude;

	@Value("${bpdts.london}")
	private String londonName;

	@Test
	public void getAllUsers() throws Exception {
		this.mockMvc.perform(get("/api/v1/users")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getUsersWithinLondon() throws Exception {
		this.mockMvc.perform(get("/api/v1/getUsersWithinLondonRadius").param("radMiles", "20")).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is(266)));
	}

	@Test
	public void getUsersWithinRadius() throws Exception {
		this.mockMvc
				.perform(get("/api/v1/coords/users?").param("lat", Double.toString(londonLatitude))
						.param("lng", Double.toString(londonLongitude)).param("radMiles", "20"))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is(266)));
	}

	@Test
	public void getCityListedUsers() throws Exception {
		this.mockMvc.perform(get("/api/v1/city/Reading/users")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(83)));
	}

	@Test
	public void getLondonListedUsers() throws Exception {
		this.mockMvc.perform(get("/api/v1/getLondonListedUsers")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(135)));
	}
}
