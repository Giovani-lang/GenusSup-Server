package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {
    @Query(value = "SELECT r FROM Reclamation r WHERE r.note.etudiant.ecole.id =:e ORDER BY r.id DESC")
    List<Reclamation> findByEcole (@Param("e") Long ecoleId);
    @Query(value = "SELECT r FROM Reclamation r WHERE r.note.matiere.enseignant.id =:i ORDER BY r.id DESC")
    List<Reclamation> findByEnseignant (@Param("i") Long ensId);
    @Query(value = "SELECT r FROM Reclamation r WHERE r.note.etudiant.id =:i ORDER BY r.id DESC")
    List<Reclamation> findByEtudiant (@Param("i") Long etdId);
    @Query(value = "SELECT r FROM Reclamation r WHERE r.note.id =:i")
    Optional<Reclamation> findByNote (@Param("i") Long id);
}
