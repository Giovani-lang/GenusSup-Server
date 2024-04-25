package com.logonedigital.PI.SCHULE.Traces.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logonedigital.PI.SCHULE.Traces.Model.GenusHttpTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Repository
@Slf4j
public class GenusHttpTraceRepository implements HttpExchangeRepository {

    AtomicReference<HttpExchange> lastTrace = new AtomicReference<>();

    @Autowired
    GenusHttpTraceBaseRepository genusHttpTraceBaseRepository;

    @Override
    public List<HttpExchange> findAll() {
        return Collections.singletonList(lastTrace.get());
    }

    @Override
    public void add(HttpExchange trace) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            lastTrace.set(trace);
            GenusHttpTrace entity = GenusHttpTrace.builder().withTimestamp(
                            Date.from(trace.getTimestamp())
                    ).withPrincipal(
                            trace.getPrincipal().getName()
                    )
                    .withApiPath(trace.getRequest().getUri().toString())
                    .withRequest(mapper.writeValueAsString(trace.getRequest()))
                    .withSession(mapper.writeValueAsString(trace.getSession()))
                    .withResponse(mapper.writeValueAsString(trace.getResponse()))
                    .build();
            genusHttpTraceBaseRepository.save(entity);
        } catch (Exception e) {
            log.error("Failed to save trace for request made at {}", trace.getTimestamp());
            e.printStackTrace();
        }
    }

}
