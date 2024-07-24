package com.genus.GENUS_PRIMO.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_parents")
public class Parent extends User{
    private String adresse,profession;
    private Date createdAt, updatedAt;
    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Etudiant etudiant;
}
