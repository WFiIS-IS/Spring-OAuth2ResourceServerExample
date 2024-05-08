package lol.wfis.springoauth2resourceserverexample.api;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Collections;

@Configuration
public class OpenApiConfig {
    @Autowired
    private Environment env;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("spring_oauth", new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .description("Oauth2 flow")
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl(env.getProperty("spring.security.oauth2.client.provider.authentik.authorization-uri"))
                                                .tokenUrl(env.getProperty("spring.security.oauth2.client.provider.authentik.token-uri"))
                                                .scopes(new Scopes()
                                                        .addString("openid", "for read operations")
                                                        .addString("email", "for write operations")
                                                        .addString("profile", "for write operations")
                                                ))
                                ))

                )
                .security(Collections.singletonList(
                        new SecurityRequirement().addList("spring_oauth")));

    }
}