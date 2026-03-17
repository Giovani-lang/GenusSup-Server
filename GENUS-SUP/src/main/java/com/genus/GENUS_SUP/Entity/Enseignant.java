package com.genus.GENUS_SUP.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_enseignants")
@Entity
public  class Enseignant extends User {
    private String grade;
    private Date createdAt,updatedAt;
    @OneToMany(mappedBy = "enseignant",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    List<Matiere> matieres = new ArrayList<>();
    @OneToMany(mappedBy = "enseignant",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<Emargement> emargements = new ArrayList<>();
}
