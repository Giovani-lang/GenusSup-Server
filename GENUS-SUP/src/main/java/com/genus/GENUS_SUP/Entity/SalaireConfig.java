package com.genus.GENUS_SUP.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_config_salaire")

public class SalaireConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int jour;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Ecole ecole;
}
