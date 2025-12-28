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
//                 .servers(List.of(
//                         new Server().url("https://9185.pro604cr.amypo.ai/")
//                 ));
//         }
// }



// package com.example.demo.config;

// import io.swagger.v3.oas.models.*;
// import io.swagger.v3.oas.models.security.*;
// import org.springframework.context.annotation.*;

// @Configuration
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
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
 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import io.swagger.v3.oas.models.Components; 
import io.swagger.v3.oas.models.OpenAPI; 
import io.swagger.v3.oas.models.servers.Server; 
import io.swagger.v3.oas.models.security.SecurityRequirement; 
import io.swagger.v3.oas.models.security.SecurityScheme; 
import java.util.List; 
 
@Configuration 
public class OpenApiConfig { 
 
    @Bean 
    public OpenAPI customOpenAPI() { 
        // Security scheme for JWT 
        SecurityScheme bearerAuth = new SecurityScheme() 
                .type(SecurityScheme.Type.HTTP) 
                .scheme("bearer") 
                .bearerFormat("JWT"); 
 
        return new OpenAPI() 
                .components(new Components().addSecuritySchemes("bearerAuth", 
            bearerAuth)) 
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth")) 
                .servers(List.of(new Server().url("https://9185.pro604cr.amypo.ai/"))); // your 
        server URL 
} 
}