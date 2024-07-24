package com.genus.GENUS_PRIMO.Repository;

import com.genus.GENUS_PRIMO.Entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    @Query(value = "SELECT * FROM `tb_historique_des_paiements` WHERE `etudiant_id` =:i AND `annee_academique_id` =:a ",nativeQuery = true)
    List<Paiement> findPaiementByMatricule(@Param("i") Long etudiantIdd,@Param("a")Long anneeId);
}
