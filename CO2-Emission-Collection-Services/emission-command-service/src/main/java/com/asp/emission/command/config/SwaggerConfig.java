package com.asp.emission.command.config;


import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.List;

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
        }).select().apis(RequestHandlerSelectors.basePackage("com.asp.emission.command")).paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Co2-Emission-Collection-Service", "Co2-Emission-Collection-Service", "v1", null, null, null, null, Collections.emptyList());
    }






}
