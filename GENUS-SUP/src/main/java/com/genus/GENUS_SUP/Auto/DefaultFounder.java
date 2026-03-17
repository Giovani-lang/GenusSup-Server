package com.genus.GENUS_SUP.Auto;

import com.genus.GENUS_SUP.Entity.Founder;
import com.genus.GENUS_SUP.Mapper.FounderMapper;
import com.genus.GENUS_SUP.Repository.FounderRepository;
import com.genus.GENUS_SUP.dto.founder_dto.FounderRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@RequiredArgsConstructor
public class DefaultFounder {

    private final PasswordEncoder encoder;
    private final FounderRepository founderRepo;
    private final FounderMapper founderMapper;

@PostConstruct
public void init() {
    long count = founderRepo.countByFounder();
    if (count == 0) {
        FounderRequest founderRequest = new FounderRequest(
                null,
                null,
                "founder@genus.com",
                null,
                this.encoder.encode("1234"),
                null,
                "Actif",
                null
        );
        Founder founder = this.founderMapper.fromFounderRequest(founderRequest);
        founder.setUsername(founderRequest.getEmail());
        founder.setRole("FONDATEUR");
        founder.setCreatedAt(new Date());
        this.founderRepo.save(founder);
    }
}
}
