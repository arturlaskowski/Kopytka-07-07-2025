package pl.kopytka.common.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String traceId = getOrCreateTraceId(request);
        MDC.put("traceId", traceId);

        long start = System.currentTimeMillis();
        logger.info("[Trace-Id: {}] Incoming request: {} {}", traceId, request.getMethod(), request.getRequestURI());

        try {
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - start;
            logger.info("[Trace-Id: {}] Responded with {} in {}ms",
                    traceId, response.getStatus(), duration);
            MDC.remove("traceId");
        }
    }

    private String getOrCreateTraceId(HttpServletRequest request) {
        String traceId = request.getHeader("X-Trace-Id");
        return traceId != null ? traceId : UUID.randomUUID().toString();
    }
}
