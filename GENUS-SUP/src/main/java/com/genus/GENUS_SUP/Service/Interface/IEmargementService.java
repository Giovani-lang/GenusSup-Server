package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.emargement_dto.EmargementRequest;
import com.genus.GENUS_SUP.dto.emargement_dto.EmargementResponse;

import java.util.List;

public interface IEmargementService {

    EmargementResponse addEmargement(EmargementRequest emargementRequest);
    List<EmargementResponse> getAllEmargement(Long ecoleId);
    List<EmargementResponse> getEnseignantList(String email);
}
