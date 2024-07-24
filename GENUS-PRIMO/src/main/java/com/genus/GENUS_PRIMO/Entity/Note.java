package com.genus.GENUS_PRIMO.Entity;


import jakarta.persistence.*;
import lombok.*;


import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Table(name = "tb_notes")
@ToString
@Entity
public class
Note  implements Serializable {
    @Serial
    private static final Long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private Float pondCC,pondSN,pondRT,noteControle,noteSession,noteRattrapage;
    private String periode;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Matiere matiere;
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Etudiant etudiant;
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private AnneeAcademique anneeAcademique;
    @OneToOne(mappedBy = "note",cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Reclamation reclamation;

}
