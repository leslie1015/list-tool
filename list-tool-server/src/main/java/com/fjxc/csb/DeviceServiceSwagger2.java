package com.fjxc.csb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wangyong
 */
@Configuration
@EnableSwagger2
public class DeviceServiceSwagger2 {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.fjxc.csb.web"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        //页面标题
        .title("Spring Boot中使用Swagger2构建RESTful APIs")
        //描述
        .description("更多swagger2相关文章请访问官网：http://swagger.io/")
        //创建人
        .contact(new Contact("dev", "", "wangyy866@163.com"))
        //版本
        .version("1.0")
        .build();
  }
}
