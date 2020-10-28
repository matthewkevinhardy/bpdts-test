package bpdts.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import bpdts.service.BpdtsRestAccessor;
import bpdts.service.BpdtsRestAccessorImpl;
import bpdts.service.UserService;
import bpdts.service.UserServiceImpl;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:bpdts.properties")
public class Config {
	
	private static final Logger LOG = LoggerFactory.getLogger(Config.class);
	
	@Value("${bpdts.baseUrl}") 
	private String bpdtsBaseUrl;
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/bpdts-test/.*")).build();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public WebClient getBpdtsWebClient() {
		LOG.info("Base URL: "+bpdtsBaseUrl);
		return WebClient.builder().baseUrl(bpdtsBaseUrl).build();
	}
	
	@Bean
	public BpdtsRestAccessor getBpdtsRestAccessor() {
		return new BpdtsRestAccessorImpl(getBpdtsWebClient());
	}
	
	@Bean
	public UserService getUserService() {
		return new UserServiceImpl(getBpdtsRestAccessor());
	}
}