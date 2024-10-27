package br.com.traumfabrik.compraoq.infra;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "COMPRAOQ API",
                description = "API para sistema COMPRAOQ",
                version = "1.0.0"
        )
)
public class OpenAPIConfig {
}
