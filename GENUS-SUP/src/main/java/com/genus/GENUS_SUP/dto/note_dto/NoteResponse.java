package com.genus.GENUS_SUP.dto.note_dto;

import com.genus.GENUS_SUP.Model.AnneeAcademiqueModel;
import com.genus.GENUS_SUP.Model.EtudiantModel;
import com.genus.GENUS_SUP.Model.MatiereModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {
    private Long id;
    private EtudiantModel etudiant;
    private MatiereModel matiere;
    private String periode;
    private AnneeAcademiqueModel anneeAcademique;
    private Float noteControle,noteSession;

}
