package com.genus.GENUS_PRIMO.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_devoirs")
public class Devoir  implements Serializable {

    @Serial
    private static final Long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre, description, lien;
    private Date createdAt, updatedAt;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Matiere matiere;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private AnneeAcademique anneeAcademique;

}
