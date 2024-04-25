package com.logonedigital.PI.SCHULE.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_annees_academiques")
public class AnneeAcademique {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "annee_academique")
    private String annees;

    @OneToMany(mappedBy = "anneeAcademique",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<Paiement> paiements = new ArrayList<>();

    @OneToMany(mappedBy = "anneeAcademique",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Appartenance> appartenances = new ArrayList<>();

    @OneToMany(mappedBy = "anneeAcademique",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Planification> planifications = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Ecole ecole;

    @OneToMany(mappedBy = "anneeAcademique",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Note> notes = new ArrayList<>();

    @OneToMany(mappedBy = "anneeAcademique",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Devoir> devoirs = new ArrayList<>();


}
