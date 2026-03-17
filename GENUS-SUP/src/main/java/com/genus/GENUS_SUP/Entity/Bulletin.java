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
@Table(name = "tb_bulletins_de_paie")
public class Bulletin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Double montant;
    @ManyToOne(targetEntity = Enseignant.class, fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    public Enseignant enseignant ;
    @ManyToOne(targetEntity = AnneeAcademique.class, fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    public  AnneeAcademique anneeAcademique;

}
