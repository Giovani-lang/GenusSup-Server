package com.genus.GENUS_SUP.dto.devoir_dto;

import com.genus.GENUS_SUP.Model.AnneeAcademiqueModel;
import com.genus.GENUS_SUP.Model.MatiereModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevoirResponse {
    private Long id;
    private MatiereModel matiere;
    private String titre, description, lien;
    private AnneeAcademiqueModel anneeAcademique;
    private Date createdAt, updatedAt;
}
