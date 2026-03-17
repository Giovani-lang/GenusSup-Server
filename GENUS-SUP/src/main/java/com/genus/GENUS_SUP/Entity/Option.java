package com.genus.GENUS_SUP.Entity;

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
@Table(name = "tb_options")
public class Option implements Serializable {
    @Serial
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private boolean isDeleted = false;
    @OneToOne(mappedBy = "option",cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Tarif tarif;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Classe classe;
    @OneToMany(mappedBy = "option",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<Appartenance> appartenances = new ArrayList<>();
    @OneToMany(mappedBy = "option",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Matiere> matieres = new ArrayList<>();
}
