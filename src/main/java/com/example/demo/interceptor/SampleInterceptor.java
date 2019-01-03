package com.example.demo.interceptor;


//import javax.servlet.http.HttpServletResponse;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;



public class SampleInterceptor implements HandlerInterceptor {

    private static final Logger log = LogManager.getLogger(SampleInterceptor.class);
    
   /* @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        log.info("after rendering the view");
        
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        log.info("after handling the request");
        
    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        log.info("preHandle Method:" + arg0.getMethod());
        log.info("preHandle Scheme:" + arg0.getScheme());
        log.info("before handling the request");
        return true;
    }*/

}
