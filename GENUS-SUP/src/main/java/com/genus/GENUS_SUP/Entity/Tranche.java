package com.genus.GENUS_SUP.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tranches")
public class Tranche implements Serializable {
    @Serial
    private static final Long serialVersionUID =1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numero;
    private double montant;
    private String date;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Tarif tarif;
}
