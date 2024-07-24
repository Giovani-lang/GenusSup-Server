package com.genus.GENUS_PRIMO.Service.Interface;


import com.genus.GENUS_PRIMO.dto.matiere_dto.MatiereRequest;
import com.genus.GENUS_PRIMO.dto.matiere_dto.MatiereResponse;

import java.util.List;

public interface IMatiereService {
    MatiereResponse addMatiere (MatiereRequest matiereRequest);
    List<MatiereResponse> getMatiere ();
    List<MatiereResponse> getMatiereByEcole (Long ecoleId);
    List<MatiereResponse> getMatiereByOption (Long id);
    MatiereResponse updateMatiere (Long id,MatiereRequest matiereRequest);
    MatiereResponse deleteMatiere (Long id);
}
