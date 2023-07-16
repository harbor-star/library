package com.example.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/5/7 21:00
 */
@Data
@PropertySource("classpath:objectlist.properties")
@ConfigurationProperties(prefix = "person")
@Component
public class UnpackFileUtils {
    public Map<String, String> map = new HashMap<>();
}
