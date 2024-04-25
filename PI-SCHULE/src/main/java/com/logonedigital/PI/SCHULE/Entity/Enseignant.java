package com.logonedigital.PI.SCHULE.Entity;

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
    private boolean chiefDepartment ;
    private Date createdAt,updatedAt;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Filiere department;

    @ManyToMany(mappedBy = "enseignant",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    List<Matiere> matieres = new ArrayList<>();
}
