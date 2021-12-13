package com.asp.emission.query.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";




    @Bean
    public Docket api(Environment env, ServletContext servletContext) {
        String appBasePath = env.getProperty("server.servlet.context-path");
        logger.info(" Path {}" , appBasePath);

        return new Docket(DocumentationType.SWAGGER_2).pathProvider(new RelativePathProvider(servletContext) {
            @Override
            public String getApplicationBasePath() {
                return appBasePath;
            }
        }).select().apis(RequestHandlerSelectors.basePackage("com.asp.emission.query")).paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Co2-Emission-Query-Service", "Co2-Emission-Query-Service", "v1", null, null, null, null, Collections.emptyList());
    }






}
