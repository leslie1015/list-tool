package com.fjxc.csb.feign;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "auth-server",  path = "/auth")
public interface AuthClient {
  @GetMapping(value="/login")
  Map<String, String> loginTest(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password);

  @GetMapping(value="/certification")
  String certification(@RequestParam(value = "token") String token);
}
