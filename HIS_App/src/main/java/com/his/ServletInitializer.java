package com.his;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * This class is used initialize our spring boot application
 * @author Ashok
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

	

	/**
	 * This method is used to configure sources of our application
	 */
	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(HealthInsuranceApplication.class);
	}
}
