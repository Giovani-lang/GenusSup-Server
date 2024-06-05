package com.logonedigital.PI.SCHULE.dto.note_dto;

import com.logonedigital.PI.SCHULE.Entity.Etudiant;
import com.logonedigital.PI.SCHULE.Entity.Matiere;
import com.logonedigital.PI.SCHULE.Model.AnneeAcademiqueModel;
import com.logonedigital.PI.SCHULE.Model.EtudiantModel;
import com.logonedigital.PI.SCHULE.Model.MatiereModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
