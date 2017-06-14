package com.company.invoicing.intercepters;

import com.company.invoicing.security.JwtTokenUtil;
import com.company.invoicing.security.JwtUser;
import com.company.invoicing.security.JwtUserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by User on 6/13/2017.
 */
@Component
public class AuthorityIntercepter implements HandlerInterceptor {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String token = httpServletRequest.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        HandlerMethod hm=(HandlerMethod)o;
        Method method=hm.getMethod();
        if(method.getDeclaringClass().isAnnotationPresent(RestController.class)){
            System.out.println("username: "+user.getUsername()+" password: "+user.getPassword()+" id: "+user.getId()+" company id: "+user.getCompany_id());
            /*if(method.isAnnotationPresent(CustomAnnotation.class))
            {
                //httpServletRequest.getHeader("Authentication")
                Cookie[] cookies = httpServletRequest.getCookies();
                if(cookies != null) {
                    for (int i = 0; i < cookies.length; i++) {
                        if(cookies[i].getName().equals("id")){
                            User user=service.findByUser_id(Long.parseLong(cookies[i].getValue()));
                            for(RolePermission rp : rpservice.findAll()){
                                if(rp.getRole().getRole_id()==user.getRole().getRole_id()){
                                    System.out.println("OVO JE STA PROVERAVAM: "+method.getAnnotation(CustomAnnotation.class).value());
                                    if(rp.getPermission().getName().equals(method.getAnnotation(CustomAnnotation.class).value())){
                                        System.out.println("MOZE");
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }*/
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("posthandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("aftercompletion");
    }
}
