package com.example.hospital_accompany.resolver;

import com.example.hospital_accompany.annotation.LoginUser;
import com.example.hospital_accompany.dto.CustomUserDetails;
import com.example.hospital_accompany.entity.User;
import com.example.hospital_accompany.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@AllArgsConstructor
@Slf4j
public class CustomAuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(isAuthenticationUser(authentication)){
            log.info("Authentication 객체가 없거나, 익명 사용자 입니다.");
            return null;
        }

        User user = getUserFromAuthentication(authentication);

        log.info("user name = {}",user.getUsername());

        return user;
    }

    private User getUserFromAuthentication(Authentication authentication) {
        // jwt token 추출
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();

        return userRepository.findByUsername(username).orElseThrow();
    }

    private boolean isAuthenticationUser(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }
}
