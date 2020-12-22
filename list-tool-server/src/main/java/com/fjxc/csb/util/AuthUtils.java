package com.fjxc.csb.util;


import com.fjxc.csb.domain.UserThreadLocal;

public class AuthUtils {

  public static String getCurrentUser() {

    return UserThreadLocal.get().get("sub").toString();
  }

}
