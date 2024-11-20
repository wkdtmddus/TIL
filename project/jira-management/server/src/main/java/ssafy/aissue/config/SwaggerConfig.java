package ssafy.aissue.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Configuration
@Slf4j
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(ServletContext servletContext){
        log.debug(">>> [SwaggerConfig::openAPI] OpenAPI 설정");
        String contextPath = servletContext.getContextPath();
        // 로컬 서버 설정
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Local Server");
        // 배포된 서버 설정
        Server productionServer = new Server()
                .url("https://k11a403.p.ssafy.io/api")  // 실제 배포된 서버 URL로 변경
                .description("Production Server");
        return new OpenAPI().servers(List.of(localServer, productionServer))
                .info(info())
                .addSecurityItem(securityItem())
                .components(new Components()
                        .addSecuritySchemes("Authorization(oauthAccessToken)", securityScheme()));
    }

    private Info info() {
        return new Info()
                .title("AISSUE Client API")
                .version("v1")
                .description("SSAFY A403 AISSUE Client API Document")
                .license(license());
    }
    private License license() {
        return new License()
                .url("https://lab.ssafy.com/s11-final/S11P31A403")
                .name("A403");
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);
    }

    private SecurityRequirement securityItem() {
        return new SecurityRequirement()
                .addList("Authorization(oauthAccessToken)");
    }
}
