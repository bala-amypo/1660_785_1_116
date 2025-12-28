// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.servers.Server;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import java.util.List;

// @Configuration
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 // You need to change the port as per your server
                // .servers(List.of(
                //         new Server().url("https://9185.pro604cr.amypo.ai/")
                // ));
//         }
// }



// package com.example.demo.config;

// import io.swagger.v3.oas.models.*;
// import io.swagger.v3.oas.models.security.*;
// import org.springframework.context.annotation.*;

// import java.util.List;
// import io.swagger.v3.oas.models.servers.Server;

// @Configuration
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//             .servers(List.of(
//                         new Server().url("https://9185.pro604cr.amypo.ai/")
//                 ))
//             .components(new Components()
//                 .addSecuritySchemes("bearer",
//                     new SecurityScheme()
//                         .type(SecurityScheme.Type.HTTP)
//                         .scheme("bearer")
//                         .bearerFormat("JWT")))
//             .addSecurityItem(new SecurityRequirement().addList("bearer"));
//     }
// }


package com.example.demo.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Configuration
public class OpenApiConfig {

    // Updated default to port 9001
    @Bean
    public OpenAPI customOpenAPI(@Value("${app.url:http://localhost:9001}") String appUrl) {
        return new OpenAPI()
            .servers(List.of(
                new Server().url(appUrl)
            ))
            .components(new Components()
                .addSecuritySchemes("bearer",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
            .addSecurityItem(new SecurityRequirement().addList("bearer"));
    }
}
