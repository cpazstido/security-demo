package com.cf.security.service.Impl;

import com.cf.security.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        boolean hasPermission = false;
        if (principal != null) {
            if (CollectionUtils.isEmpty(grantedAuthorityList)) {
                System.out.println("角色为空");
                return hasPermission;
            }

            //根据角色查找对应的权限url
            Set adminUrls = new HashSet();
            adminUrls.add("/user");
            adminUrls.add("/admin");
            adminUrls.add("/demo");

            Set demoUrls = new HashSet();
            demoUrls.add("/demo");
            Map<String, Set> urls = new HashMap();
            urls.put("admin", adminUrls);
            urls.put("demo", demoUrls);

            Set<String> hasUrls = new HashSet<>();
            for (SimpleGrantedAuthority authority : grantedAuthorityList) {
                Set<String> hasUrl = urls.get(authority.getAuthority());
                if (hasUrl != null) {
                    hasUrls.addAll(hasUrl);
                }
            }

            for (String url : hasUrls) {
                if (url.compareTo(request.getRequestURI()) == 0) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
