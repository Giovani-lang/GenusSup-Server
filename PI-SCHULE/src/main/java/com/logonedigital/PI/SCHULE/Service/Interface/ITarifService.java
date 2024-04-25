package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.Entity.Tarif;
import com.logonedigital.PI.SCHULE.dto.tarif_dto.TarifRequest;
import com.logonedigital.PI.SCHULE.dto.tarif_dto.TarifResponse;

import java.util.List;

public interface ITarifService {

    TarifResponse addTarif (TarifRequest tarifRequest);
    List<TarifResponse> getAll (Long ecoleId);
    TarifResponse editTarif(Long id, TarifRequest tarif);
}
