package com.genus.GENUS_SUP.dto.bulletin_dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.genus.GENUS_SUP.Model.AnneeAcademiqueModel;
import com.genus.GENUS_SUP.Model.EnseignantModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BulletinResponse {
    private Long id;
    private EnseignantModel enseignant;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private Double montant;
    private AnneeAcademiqueModel anneeAcademique;
}
