package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.FicheDePresence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FicheDePresenceRepository extends JpaRepository<FicheDePresence,Long> {
    @Query(value = "SELECT f FROM FicheDePresence f WHERE f.etudiant.matricule =:e ORDER BY f.id DESC")
    List<FicheDePresence> findByEtudiant (@Param("e")String matricule);
    @Query(value = "SELECT f FROM FicheDePresence f WHERE f.etudiant.ecole.id =:e ORDER BY f.id DESC")
    List<FicheDePresence> fecthAll (@Param("e")Long ecoleId);
}
