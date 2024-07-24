package com.genus.GENUS_PRIMO.Traces.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genus.GENUS_PRIMO.Traces.Model.GenusHttpTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Repository
@Slf4j
@RequiredArgsConstructor
public class GenusHttpTraceRepository implements HttpExchangeRepository {

    private final GenusHttpTraceBaseRepository genusHttpTraceBaseRepository;

    AtomicReference<HttpExchange> lastTrace = new AtomicReference<>();

    @Override
    public List<HttpExchange> findAll() {
        return Collections.singletonList(lastTrace.get());
    }

    @Override
    public void add(HttpExchange trace) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            lastTrace.set(trace);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String remoteAddress = "unknown";
            String sessionId = "no-session";

            // Extract remoteAddress and sessionId from WebAuthenticationDetails if available
            if (authentication != null && authentication.getDetails() instanceof WebAuthenticationDetails) {
                WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
                remoteAddress = details.getRemoteAddress();
                sessionId = details.getSessionId();
            }
            GenusHttpTrace entity = GenusHttpTrace.builder().withTimestamp(
                            Date.from(trace.getTimestamp())
                    )
                    .withPrincipal(authentication.getPrincipal().toString())
                    .withApiPath(trace.getRequest().getUri().toString())
                    .withRequest(mapper.writeValueAsString(trace.getRequest()))
                    .withSession(sessionId)
                    .withRemoteAddress(remoteAddress)
                    .withResponse(mapper.writeValueAsString(trace.getResponse()))
                    .build();;
            genusHttpTraceBaseRepository.save(entity);
        } catch (Exception e) {
            log.error("Failed to save trace for request made at {}", trace.getTimestamp());
            e.printStackTrace();
        }
    }

}
