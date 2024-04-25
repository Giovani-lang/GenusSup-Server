package com.logonedigital.PI.SCHULE.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_planifications")
public class Planification implements Serializable {
    @Serial
    private static final Long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Jour,debut,fin,salle;
    private int duree;
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Matiere matiere;
    @OneToMany(mappedBy = "planification",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<FicheDePresence> ficheDePresences = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private AnneeAcademique anneeAcademique;

}
