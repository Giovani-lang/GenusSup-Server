package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.devoir_dto.DevoirRequest;
import com.genus.GENUS_PRIMO.dto.devoir_dto.DevoirResponse;

import java.util.List;

public interface IDevoirService {

    DevoirResponse addDevoir (DevoirRequest devoirRequest);
    DevoirResponse editDevoir (Long id,DevoirRequest devoirRequest);
    List<DevoirResponse> listDevoirByEtudiant (Long anneeId, Long optionId);
    List<DevoirResponse> listDevoirByOption (Long optionId);
}
