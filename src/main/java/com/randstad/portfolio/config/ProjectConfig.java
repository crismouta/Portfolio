package com.randstad.portfolio.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {
    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap();
        config.put("cloud_name", "dzwnjzo4c");
        config.put("api_key", "778277718933323");
        config.put("api_secret", "jwZ8rIVCSO-cHGa0YHBsiqms0b8");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
