package com.example.testprojcet.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class HttpInterceptor implements HandlerInterceptor {

    private final Logger LOGGER = LoggerFactory.getLogger(HttpInterceptor.class);

    // 메서드가 실행되기 전 동작하는 핸들러
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("[preHandle] preHandle is performed");
        // Request가 가진 메서드 출력
        LOGGER.info("[preHandle] request : {}", request);
        LOGGER.info("[preHandle] request path info : {}", request.getPathInfo());
        LOGGER.info("[preHandle] request header names : {}", request.getHeaderNames());
        LOGGER.info("[preHandle] request request URL : {}", request.getRequestURL());
        LOGGER.info("[preHandle] request request URI: {}", request.getRequestURI());
        LOGGER.info("[preHandle] request Requested Session Id : {}", request.getRequestedSessionId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("[postHandle] postHandle is performed");
        // Request와 Response 출력
        LOGGER.info("[postHandle] request : {}", request);
        LOGGER.info("[postHandle] response : {}", response);
        LOGGER.info("[postHandle] response : {}", response.getHeaderNames());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("[afterCompletion] afterCompletion is performed");
    }
}