package com.genus.GENUS_PRIMO.Entity;


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
@Table(name = "tb_classes")
public class Classe implements Serializable {
    @Serial
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int niveau;
    private boolean isDeleted = false;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private Cycle cycle;
    @OneToMany(mappedBy = "classe",fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<Option> options = new ArrayList<>();

}
