package com.genus.GENUS_SUP.Traces.Configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.Include;
import org.springframework.boot.actuate.web.exchanges.servlet.HttpExchangesFilter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class HttpTraceRequestFilter extends HttpExchangesFilter {
    public HttpTraceRequestFilter(HttpExchangeRepository repository, Set<Include> includes) {
        super(repository, includes);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().contains("actuator");
    }
}
