package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.tranche_dto.TrancheRequest;
import com.genus.GENUS_SUP.dto.tranche_dto.TrancheResponse;

import java.util.List;

public interface ITrancheService {
    TrancheResponse addTranche (TrancheRequest trancheRequest);
    TrancheResponse editTranche (Long id,TrancheRequest trancheRequest);
    List<TrancheResponse> search (Long tarifId);


}
