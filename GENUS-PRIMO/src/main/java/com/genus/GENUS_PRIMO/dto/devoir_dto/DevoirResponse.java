package com.genus.GENUS_PRIMO.dto.devoir_dto;

import com.genus.GENUS_PRIMO.Model.AnneeAcademiqueModel;
import com.genus.GENUS_PRIMO.Model.MatiereModel;
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
