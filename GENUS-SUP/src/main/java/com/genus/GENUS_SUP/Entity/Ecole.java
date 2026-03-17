package com.genus.GENUS_SUP.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_ecoles")
public class Ecole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image_url,name,acronym,email,site,address,fax,phone,responsible,responsible_phone,country,city;
    private Long po_box;
    private Date createdAt, updatedAt;
    @OneToOne(mappedBy = "ecole",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    SalaireConfig salaireConfig;
    @OneToMany(mappedBy = "ecole",cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    List<Campus> campus = new ArrayList<>();
    @OneToMany(mappedBy = "ecole",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    List<AnneeAcademique> anneeAcademiques = new ArrayList<>();
}
