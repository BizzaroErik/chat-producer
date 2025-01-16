package com.bizzaroerik.chatproducer.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.servers.ServerVariable;
import io.swagger.v3.oas.models.servers.ServerVariables;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@Component
public class SwaggerConfig {

    @Value("${info.app.name}")
    private final String appName = "chat-producer";
    @Value("${info.app.version}")
    private final String version;
    @Value("${info.app.host:}")
    private final String appHost;
    @Value("${info.app.base-path:}")
    private final String appBasePath = "";

    @Bean
    public OpenAPI apiEndpointsInfo() {
        ServerVariable serverVariable = new ServerVariable();
        serverVariable.setDefault(appBasePath);
        OpenAPI openApi = new OpenAPI()
                .info(new Info().title(appName)
                        .description("Chat app")
                        .version(version));
        return openApi.servers(Collections.singletonList(new Server().url(appHost)
                .variables(new ServerVariables()
                        .addServerVariable("basePath", serverVariable))));
    }
}
