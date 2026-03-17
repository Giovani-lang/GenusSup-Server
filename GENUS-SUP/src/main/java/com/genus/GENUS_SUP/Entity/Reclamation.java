package com.genus.GENUS_SUP.Entity;

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
@Table(name = "tb_reclamations")
public class Reclamation implements Serializable {
    @Serial
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motif;
    private String status;
    private String resolution;
    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Note note;
    private Date claimedAt, treatedAt;

}
