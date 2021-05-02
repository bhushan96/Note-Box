package com.project.notes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(exclude = {KafkaAutoConfiguration.class, CassandraDataAutoConfiguration.class})
@ComponentScan(basePackages = {"com.project"})
@EnableMongoRepositories(basePackages = {"com.project"})
@EnableSwagger2
public class NotesApplication {

	/**
	 * The version.
	 */
	@Value("${spring.application.version}")
	private String version;

	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}
	/**
	 * Api.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket api() {

		return
				new Docket(DocumentationType.SWAGGER_2)
						.select()
						.paths(PathSelectors.any())
						.build()
						.apiInfo(apiInfo())
						.useDefaultResponseMessages(false)
				;
		// @formatter:on
	}


	/**
	 * Cors filter.
	 *
	 * @return the cors filter
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	/**
	 * Api info.
	 *
	 * @return the api info
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Note-Box").description("FMS APIs").version(version).build();
	}

}
