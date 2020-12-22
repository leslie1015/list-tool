package com.fjxc.csb.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fjxc.csb.domain.ActionResult;
import com.fjxc.csb.domain.UserThreadLocal;
import com.fjxc.csb.domain.enumerate.ResultCode;
import com.fjxc.csb.feign.AuthClient;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class LoginInterceptor implements HandlerInterceptor {

  @Resource
  private AuthClient authClient;

  private static final String ADMIN_FLAG_KEY = "qald";

  @Override
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object o) throws IOException {
    PrintWriter writer = null;
    String token = request.getHeader("token");
    try {
      String claims = authClient.certification(token);
      JSONObject jsonObject = JSONObject.parseObject(claims);
      UserThreadLocal.set(jsonObject);
      //是否管理员。0否1是
      if (!jsonObject.get(ADMIN_FLAG_KEY).equals("1")) {
        throw new Exception("无权限");
      }

    } catch (Exception e) {
      writer = response.getWriter();
      writer.print(getResult(ResultCode.LOGIN_FAILURE.getCode(), "认证失败!", null));
      return false;
    } finally {
      if (null != writer) {
        writer.close();
      }
    }
    return true;
  }

  private String getResult(int code, String errorInfo, String val) {
    return JSON.toJSONString(new ActionResult(code, errorInfo, val));
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
      throws Exception {

  }

  //    在请求处理之后,视图渲染之前执行
  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

  }

}
