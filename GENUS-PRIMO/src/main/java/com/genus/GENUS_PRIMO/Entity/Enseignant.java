package com.genus.GENUS_PRIMO.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_enseignants")
@Entity
public  class Enseignant extends User {
    private String grade;
    private Date createdAt,updatedAt;
    @ManyToMany(mappedBy = "enseignant",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    List<Matiere> matieres = new ArrayList<>();
}
