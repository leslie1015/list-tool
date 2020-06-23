package com.fjxc.csb.web;


import com.fjxc.csb.domain.ActionResult;
import com.fjxc.csb.domain.enumerate.ResultCode;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.apache.commons.lang3.StringUtils;
/**
 * @author wangyong
 */
public class BasicController {

  @Autowired
  private HttpServletRequest request;

//  public String view(String viewName) {
//    Class clazz = this.getClass();
//    ViewFolder viewFolder = (ViewFolder)clazz.getAnnotation(ViewFolder.class);
//    if (null == viewFolder || StringUtils.isBlank(viewFolder.value())) {
//      return viewName;
//    }
//    return viewFolder.value() + "/" + viewName;
//  }

  public <T> ActionResult<T> actionResult(ResultCode code, T value){
    return  new ActionResult<T>(code.getCode(),
        code.getDesc(),
        value);
  }
  public <T> ActionResult<T> actionResult(T value){
    ResultCode code = ResultCode.SUCCESS;
    return  actionResult(code,value);
  }

  public ActionResult actionResult(ResultCode code){
    return  actionResult(code,code.getDesc());
  }

  public String getUserId(HttpServletRequest request){
    String userId = (String)request.getHeader("x-user-id");
    return userId;
  }

  public <T> ActionResult actionResultWithBindingResult(ResultCode code, BindingResult bindingResult){
    String errMsg = getBindingResultErrors(bindingResult);
    if(StringUtils.isBlank(errMsg)){
      return actionResult(code);
    }
    return actionResultDesc(code, errMsg);
  }
  public String getBindingResultErrors(BindingResult bindingResult){
    if (null == bindingResult || !bindingResult.hasErrors()) {
      return StringUtils.EMPTY;
    }
    StringBuilder errorStrBuilder = new StringBuilder();
    List<ObjectError> errorList = bindingResult.getAllErrors();
    for (ObjectError error : errorList) {
      errorStrBuilder.append(error.getDefaultMessage() + ";");
    }
    return StringUtils.removeEnd(errorStrBuilder.toString(), ";");
  }

  public <T> ActionResult<T> actionResultDesc(ResultCode code, T value){
    if (code != ResultCode.SUCCESS
        && value != null
        && StringUtils.isNotBlank(value.toString())) {
      return new ActionResult<>(code.getCode(),
          value.toString(),
          value);
    }
    return new ActionResult<>(code.getCode(),
        code.getDesc(),
        value);
  }
}