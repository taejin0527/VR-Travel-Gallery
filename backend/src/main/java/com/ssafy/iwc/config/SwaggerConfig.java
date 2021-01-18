package com.ssafy.iwc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
//	Swagger 설정 확인
//	http://localhost:8000/{your-app-root}/v2/api-docs
//	http://localhost:8080/iwc/v2/api-docs?group=V1
//	Swagger-UI 확인
//	http://localhost:8080/{your-app-root}/swagger-ui.html
//	http://localhost:8080/iwc/swagger-ui.html
	
	private String version = "V0.1.0";
	private String title = "SSAFY API " + version;
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.groupName(version)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.iwc.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(title)
				.description("API Reference for team WEEKENDOFF Developers")
				.license("SSAFY License")
				.licenseUrl("ssafy@ssafy.com").build();
	}
}
