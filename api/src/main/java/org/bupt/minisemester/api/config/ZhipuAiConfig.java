package org.bupt.minisemester.api.config;

import com.zhipu.oapi.ClientV4;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZhipuAiConfig {

    @Value("${zhipuai.api.secret-key}")
    private String apiSecretKey;

    @Bean
    public ClientV4 zhipuAiClient() {
        return new ClientV4.Builder(apiSecretKey).build();
    }
}
