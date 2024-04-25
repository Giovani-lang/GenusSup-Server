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
@Table(name = "tb_matieres")
public class Matiere implements Serializable {
    @Serial
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String module,code,intitule;
    private int coefficient;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Option option;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Enseignant enseignant;

    @OneToMany(mappedBy = "matiere",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<Note> notes = new ArrayList<>();

    @OneToMany(mappedBy = "matiere",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<Planification> planifications = new ArrayList<>();

    @OneToMany(mappedBy = "matiere",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<Devoir> devoirs = new ArrayList<>();
}
