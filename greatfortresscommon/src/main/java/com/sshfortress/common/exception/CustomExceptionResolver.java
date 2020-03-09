package com.sshfortress.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver
  implements HandlerExceptionResolver
{
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception)
  {
    CustomException customException = null;
    if ((exception instanceof CustomException)) {
      customException = (CustomException)exception;
    } else {
    	 customException = new CustomException("未知错误请于，系统管理员联系！");
    }
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("message", customException.getMessage());
    modelAndView.setViewName("error");
    return modelAndView;
  }
}
