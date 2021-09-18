package com.personal_finances.config;

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

    private final String TITLE = "Finances API REST";
    private final String DESCRIPTION = "API REST para consumo e envio de informações para o projeto de financias pessoais. " +
            "Nos cadastros, o campo ID é pra ser setado como nulo, pois o próprio banco irá gerar o id.";
    private final String LICENCEURL = "https://www.apache.org/licenses/LICENSE-2.0\"";
    private final String VERSION = "1.0.0";

    @Bean
    public Docket financesApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.personal_finances")).paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        ApiInfo apiInfo = new ApiInfoBuilder().title(TITLE)
                .description(DESCRIPTION).version(VERSION)
                .license("Apache License Version 2.0").licenseUrl(LICENCEURL)
                // DEFINIR CONTRATO
                .build();

        return apiInfo;
    }
}
