package com.springboot.university.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.Collections;

/**
 * link to access to the documentation
 * HTML= http://localhost:8080/swagger-ui/index.html
 *
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails(){
        return new ApiInfo("Spring Boot University",
                "Documentation of API-REST of an University",
                "1.0",
                "https://github.com/ccor27",
                new Contact("Cristian","https://www.linkedin.com/in/cristian-osorio-ramirez","cristian@gmail.com"),
                "MIT",
                "https://github.com/ccor27",
                Collections.emptyList());
    }
}
