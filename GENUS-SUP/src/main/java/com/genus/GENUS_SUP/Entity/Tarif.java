package com.genus.GENUS_SUP.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "tb_tarifs")
public class Tarif {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double montant;
    private boolean isDeleted = false;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Option option;
    @OneToMany(mappedBy = "tarif", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tranche> tranches = new ArrayList<>();
}
