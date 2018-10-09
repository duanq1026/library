package com.hniu.config;

import com.fasterxml.jackson.databind.JavaType;
import com.hniu.entity.System;
import com.hniu.mapper.SystemMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.UnknownHostException;
import java.util.List;

@Configuration
public class ApplicationConfig {


    @Bean
    public System system(SystemMapper sm) {
        try {
            List<System> systems = sm.selectAll();
            System system = systems.get(0);
            return system;
        } catch (Exception e) {
            return new System(1, "图书管理系统", "/img/icon.icon", new Byte("10"), "appid", "appsecret", "HNIUBS",new Float(0));
        }
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("*").allowCredentials(true);
            }
        };
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
