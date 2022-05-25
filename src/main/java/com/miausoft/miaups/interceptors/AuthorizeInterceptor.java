package com.miausoft.miaups.interceptors;

import com.miausoft.miaups.exceptions.UnauthorizedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AuthorizeInterceptor {

    private static String PREFIX = "APPROLE_";
    private static String ANON = "ROLE_ANONYMOUS";

    @Around("@annotation(authorize)")
    public Object trace(ProceedingJoinPoint joinPoint, Authorize authorize) throws Throwable {

        String[] roles = authorize.roles();
        if (roles == null) {
            roles = new String[]{};
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Boolean hasAnyRole = Arrays.stream(roles).anyMatch(
                role -> auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(PREFIX + role))
        );

        if (hasAnyRole && hasRolesAndNotAnon(auth)) {
            return joinPoint.proceed();

        }

        throw new UnauthorizedException();
    }


    private Boolean hasRolesAndNotAnon(Authentication auth) {
        return auth.getAuthorities() != null &&
            auth.getAuthorities().size() > 0 &&
            !auth.getAuthorities().stream().anyMatch(
                role -> role.getAuthority().equals(ANON)
            );
    }

}
