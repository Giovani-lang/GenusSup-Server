package com.genus.GENUS_PRIMO.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_etudiants")
@Entity
public class Etudiant extends User {

    private String matricule;
    private String dateNaissance;
    private List<String> dossiers;
    private Date createdAt;
    private Date updatedAt;
    @OneToOne(mappedBy = "etudiant",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Parent parent;
    @OneToMany(mappedBy = "etudiant",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<Paiement> paiements = new ArrayList<>();
    @OneToMany(mappedBy = "etudiant",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<FicheDePresence> ficheDePresences = new ArrayList<>();
    @OneToMany(mappedBy = "etudiant",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<Appartenance> appartenances = new ArrayList<>();
    @OneToMany(mappedBy = "etudiant",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<Note> notes = new ArrayList<>();

}
