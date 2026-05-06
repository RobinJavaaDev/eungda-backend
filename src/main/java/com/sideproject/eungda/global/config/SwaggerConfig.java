// 경로: src/main/java/com/sideproject/eungda/global/config/SwaggerConfig.java
package com.sideproject.eungda.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI eungdaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Eungda(응다) API 명세서")
                        .description("장 건강 및 식단 기록 추적 서비스 Eungda의 API 문서입니다.")
                        .version("v1.0.0"));
    }
}