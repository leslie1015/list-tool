package com.fjxc.csb.domain;

import com.alibaba.fastjson.JSONObject;

public class UserThreadLocal {

  private static final ThreadLocal<JSONObject> LOCAL = new ThreadLocal();


  private UserThreadLocal(){

  }

  public static void set(JSONObject user){
    LOCAL.set(user);;
  }

  public static JSONObject get(){
    if (null == LOCAL.get()) {
      return new JSONObject();
    }
    return LOCAL.get();
  }

}
