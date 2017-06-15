package com.company.invoicing.intercepters;

import com.company.invoicing.models.RolePermission;
import com.company.invoicing.security.JwtTokenUtil;
import com.company.invoicing.security.JwtUser;
import com.company.invoicing.security.JwtUserDetailsServiceImpl;
import com.company.invoicing.services.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //logger.info("prehandle");
        String token = httpServletRequest.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        HandlerMethod hm=(HandlerMethod)o;
        Method method=hm.getMethod();
        if(method.getDeclaringClass().isAnnotationPresent(RestController.class)){
            if(method.isAnnotationPresent(AuthorityAnnotation.class)){
                if(user.getRole().getName().equals("superuser")){
                    return true;
                }else{
                    for(RolePermission rp:rolePermissionService.findAll()){
                        if(rp.getRole().getName().toLowerCase().equals(user.getRole().getName().toLowerCase()) && rp.getTabela().getName().toLowerCase().equals(method.getAnnotation(AuthorityAnnotation.class).table())){
                            if(rp.getPermission().getName().equals(method.getAnnotation(AuthorityAnnotation.class).method()) || rp.getPermission().getName().equals("all"))
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //logger.info("posthandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //logger.info("aftercompletion");
    }
}
