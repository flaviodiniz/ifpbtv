package com.ads.ifpbtv.configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ads.ifpbtv.IfpbtvApplication;

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
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(IfpbtvApplication.class.getPackage().getName()))
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, Collections.emptyList())
				.globalResponseMessage(RequestMethod.HEAD, Collections.emptyList())
				.globalResponseMessage(RequestMethod.POST, Collections.emptyList())
				.globalResponseMessage(RequestMethod.PUT, Collections.emptyList())
				.globalResponseMessage(RequestMethod.PATCH, Collections.emptyList())
				.globalResponseMessage(RequestMethod.DELETE, Collections.emptyList())
				.globalResponseMessage(RequestMethod.OPTIONS, Collections.emptyList())
				.globalResponseMessage(RequestMethod.TRACE, Collections.emptyList())
				.apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("IFPB - TV")
				.description("Projeto referente a disciplina de Projeto II - ADS. " + getDataAtual())
				.version("1.0")
				.build();
	}

	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedMethods("*")
				.allowedOrigins("*");
			}
		};
	}
	
	
	private String getDataAtual() {
		
		LocalDateTime agora = LocalDateTime.now();

		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = formatterData.format(agora);
		
		return dataFormatada;
	}
}
