package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.tarif_dto.TarifRequest;
import com.genus.GENUS_SUP.dto.tarif_dto.TarifResponse;

import java.util.List;

public interface ITarifService {

    TarifResponse addTarif (TarifRequest tarifRequest);
    List<TarifResponse> getAll (Long ecoleId);
    TarifResponse editTarif(Long id, TarifRequest tarif);
    TarifResponse deleteTarif(Long id);
}
