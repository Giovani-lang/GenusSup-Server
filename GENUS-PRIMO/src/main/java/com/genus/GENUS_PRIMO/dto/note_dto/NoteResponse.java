package com.genus.GENUS_PRIMO.dto.note_dto;

import com.genus.GENUS_PRIMO.Model.AnneeAcademiqueModel;
import com.genus.GENUS_PRIMO.Model.EtudiantModel;
import com.genus.GENUS_PRIMO.Model.MatiereModel;
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
    private Float pondCC,pondSN,pondRT,noteControle,noteSession,noteRattrapage;

}
