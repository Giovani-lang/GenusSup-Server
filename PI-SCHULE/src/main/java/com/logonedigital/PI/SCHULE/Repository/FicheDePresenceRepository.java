package com.logonedigital.PI.SCHULE.Repository;

import com.logonedigital.PI.SCHULE.Entity.FicheDePresence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FicheDePresenceRepository extends JpaRepository<FicheDePresence,Long> {
    @Query(value = "SELECT f FROM FicheDePresence f WHERE f.etudiant.email =:e")
    List<FicheDePresence> findByEtudiant (@Param("e")String email);
    @Query(value = "SELECT f FROM FicheDePresence f WHERE f.etudiant.ecole.id =:e")
    List<FicheDePresence> fecthAll (@Param("e")Long ecoleId);
}
