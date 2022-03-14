package com.example.demo.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .addServersItem(new Server().url("/"))
        .info(new Info()
            .title("Accounts Transactions Demo")
            .version("1.0.0")
            .description("CoreBanking_B Api Documentation")
            .termsOfService("urn:tos")
            .license(new License().name("Apache 2.0").url("http://springdoc.org")));
  }


}