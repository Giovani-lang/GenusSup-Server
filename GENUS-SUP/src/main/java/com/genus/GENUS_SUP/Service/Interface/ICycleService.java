package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.cycle_dto.CycleRequest;
import com.genus.GENUS_SUP.dto.cycle_dto.CycleResponse;

import java.util.List;

public interface ICycleService {

    CycleResponse addCycle (CycleRequest cycleRequest);
    List<CycleResponse> getCycles(Long id);
    CycleResponse editCycle (Long id, CycleRequest cycleRequest);
    CycleResponse deleteCycle (Long id);
}
