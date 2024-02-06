package ru.chaos.app.components.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cache")
@Getter
@Setter
public class CacheConfigurationProperties {
    private int capacity;
    private int dataLossInterval;
}
