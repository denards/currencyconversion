package com.sognisport.currencyconversion.filters;


import com.sognisport.currencyconversion.logging.Slf4TransferService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CurrencyConversionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        MDC.put("requestId", UUID.randomUUID().toString());
        MDC.put("sourceIP", request.getRemoteAddr());
        new Slf4TransferService().convert(33L);
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}

