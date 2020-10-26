package bpdts;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BpdtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpdtsApplication.class, args);
	}

	

	@RestController
	class RootController {
		@RequestMapping(value = "/")
		public void redirect(HttpServletResponse response) throws IOException {
			response.sendRedirect("/swagger-ui.html");
		}
	}
}
