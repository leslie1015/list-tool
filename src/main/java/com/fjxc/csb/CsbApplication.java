package com.fjxc.csb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients(basePackages = )
public class CsbApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsbApplication.class, args);
    }

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter()
    {
        MappingJackson2HttpMessageConverter bean = new MappingJackson2HttpMessageConverter();
        bean.setPrefixJson(false);

        //设置中文编码格式
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        list.add(MediaType.TEXT_HTML);
        bean.setSupportedMediaTypes(list);
        return bean;
    }
}
