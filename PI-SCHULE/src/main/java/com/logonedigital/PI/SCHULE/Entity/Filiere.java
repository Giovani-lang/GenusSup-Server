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
@Table(name = "tb_filieres")
public class Filiere implements Serializable {
    @Serial
    private static final Long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "filiere",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Classe> classes = new ArrayList<>();
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Enseignant> enseignants = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Cycle cycle;
}
