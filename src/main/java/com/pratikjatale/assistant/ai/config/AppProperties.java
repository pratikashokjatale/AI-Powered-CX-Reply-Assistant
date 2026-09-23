package com.pratikjatale.assistant.ai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private AiProperties ai = new AiProperties();

    @Data
    public static class AiProperties {
        private String provider = "openrouter";
        private String apiKey = "mock-key";
        private String baseUrl = "https://openrouter.ai/api/v1";
        private String model = "meta-llama/llama-3.3-70b-instruct";
        private double temperature = 0.2;
        private int maxTokens = 600;
        private GuardrailProperties guardrails = new GuardrailProperties();
    }

    @Data
    public static class GuardrailProperties {
        private boolean enabled = true;
        private boolean strictRefundCheck = true;
        private boolean strictShippingCheck = true;
    }
}
