package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    @Query(value = "SELECT p FROM Paiement p WHERE p.etudiant.id =:i AND p.anneeAcademique.id =:a ORDER BY p.id DESC")
    List<Paiement> findPaiementByMatricule(@Param("i") Long etudiantIdd,@Param("a")Long anneeId);
}
