package com.logonedigital.PI.SCHULE.Model;

import com.logonedigital.PI.SCHULE.Entity.Etudiant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantModel {
    private Long id;
    private String matricule;
    private String nom;
    private String prenom;
    private EcoleModel ecole;
}
