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
@Table(name = "tb_cycles")
public class Cycle implements Serializable {
    @Serial
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private boolean isDeleted = false;
    @OneToMany(mappedBy = "cycle", cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<Filiere> filieres = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Ecole ecole;
}
