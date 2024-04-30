package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.dto.cycle_dto.CycleRequest;
import com.logonedigital.PI.SCHULE.dto.cycle_dto.CycleResponse;

import java.util.List;

public interface ICycleService {

    CycleResponse addCycle (CycleRequest cycleRequest);
    List<CycleResponse> getCycles(Long id);
    CycleResponse editCycle (Long id, CycleRequest cycleRequest);
    CycleResponse deleteCycle (Long id);
}
