package com.fjxc.csb.config;

import com.fjxc.csb.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginAdapter implements WebMvcConfigurer {


  @Autowired
  private LoginInterceptor loginInterceptor;

  // 这个方法用来注册拦截器，自定义拦截器需要通过这里添加注册才能生效
  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/register");

//    super.addInterceptors(registry);    //较新Spring Boot的版本中这里可以直接去掉，否则会报错
  }


}
