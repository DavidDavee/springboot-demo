package org.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-04-20 00:02
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DataSourceConfigurationProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
