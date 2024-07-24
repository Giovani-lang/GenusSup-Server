package com.genus.GENUS_PRIMO.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
}
