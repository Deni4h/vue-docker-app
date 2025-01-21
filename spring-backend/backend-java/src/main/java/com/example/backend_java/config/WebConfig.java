package com.example.backend_java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Path yang ingin diberi akses CORS
                .allowedOrigins("http://192.168.122.100:8087") // Sesuaikan dengan origin frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Metode yang diizinkan
                .allowedHeaders("*") // Semua header diizinkan
                .allowCredentials(true); // Izinkan kredensial seperti cookie
    }
}
