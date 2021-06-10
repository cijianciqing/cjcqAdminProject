package com.ns.cjcq.security.authentication.rabc;

import com.ns.cjcq.security.crud.domain.CJResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("rbacService")
public class MyRBACServiceImpl implements MyRBACService {

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object principal = authentication.getPrincipal();

        boolean hasPermission = false;
//        System.out.println("cj demo ==> com.ns.cjcq.security.authentication.rabc.MyRBACServiceImpl.hasPermission....");
        //有可能是匿名用户
        if(principal instanceof UserDetails){
            String username = ((UserDetails)principal).getUsername();

            //读取所有权限
            Set<String> urls = new HashSet<>();
            List<CJResource> cjResources =  new ArrayList<>();

            //测试
            CJResource cjResource00 = new CJResource();
            cjResource00.setUrl("/admin");
            cjResources.add(cjResource00);



            for(CJResource cjResource : cjResources){

                if(antPathMatcher.match(cjResource.getUrl(),request.getRequestURI())){
                    hasPermission = true;
                    System.out.println("cj ==>"+username+"==>has Permission!!!");
                    break;
                }
            }

        }
        return hasPermission;
    }
}
