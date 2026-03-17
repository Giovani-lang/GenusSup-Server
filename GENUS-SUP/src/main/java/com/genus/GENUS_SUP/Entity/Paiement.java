package com.genus.GENUS_SUP.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_historique_des_paiements")
public class Paiement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String libelle;
    private Double montant;
    @ManyToOne(targetEntity = Etudiant.class, fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    public Etudiant etudiant ;
    @ManyToOne(targetEntity = AnneeAcademique.class, fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    public  AnneeAcademique anneeAcademique;

}
