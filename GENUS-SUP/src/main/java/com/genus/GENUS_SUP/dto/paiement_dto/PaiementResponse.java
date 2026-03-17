package com.genus.GENUS_SUP.dto.paiement_dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.genus.GENUS_SUP.Model.AnneeAcademiqueModel;
import com.genus.GENUS_SUP.Model.EtudiantModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaiementResponse {
    private Long id;
    private EtudiantModel etudiant;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private String libelle;
    private Double montant;
    private AnneeAcademiqueModel anneeAcademique;
}
