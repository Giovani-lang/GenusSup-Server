package com.genus.GENUS_PRIMO.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "tb_campus")
public class Campus implements Serializable {
    @Serial
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name,address,responsible;
    private boolean isDeleted = false;
    private Date createdAt,updatedAt;
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Ecole ecole;
    @OneToMany(mappedBy = "campus", cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<Cycle> cycles = new ArrayList<>();
}
