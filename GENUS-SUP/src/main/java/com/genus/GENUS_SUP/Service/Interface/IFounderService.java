package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.founder_dto.FounderRequest;
import com.genus.GENUS_SUP.dto.founder_dto.FounderResponse;

import java.util.List;

public interface IFounderService {

    FounderResponse addFounder(FounderRequest founderRequest);
    FounderResponse getFounder(Long id);
    List<FounderResponse> getAllFounders();
    FounderResponse editFounder(String email,FounderRequest founderRequest);
}
