package by.bsu.biryuk.Lab7.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@Order(1) // порядок вызова фильтра
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest httpRequest) {
            String method = httpRequest.getMethod();
            String requestURI = httpRequest.getRequestURI();
            String queryString = httpRequest.getQueryString();

            if (queryString != null && !queryString.isEmpty()) {
                log.info("Incoming request: {} {}?{}", method, requestURI, queryString);
            } else {
                log.info("Incoming request: {} {}", method, requestURI);
            }
        }

        chain.doFilter(request, response);
    }
}


