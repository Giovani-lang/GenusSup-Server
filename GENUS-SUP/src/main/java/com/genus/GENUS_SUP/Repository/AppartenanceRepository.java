package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Appartenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppartenanceRepository extends JpaRepository<Appartenance,Long> {
    @Query(value = "SELECT a FROM Appartenance a WHERE a.option.classe.filiere.cycle.departement.campus.ecole.id =:e AND a.anneeAcademique.id =:a ORDER BY a.id DESC")
    List<Appartenance> findByAnneeId (@Param("e") Long ecoleId,@Param("a") Long anneId);
    @Query(value = "SELECT a FROM Appartenance a WHERE a.option.classe.filiere.cycle.departement.campus.ecole.id =:e ORDER BY a.id DESC")
    List<Appartenance> findByEcole (@Param("e") Long ecoleId);
    @Query(value = "SELECT * FROM `tb_appartenances` WHERE `annee_academique_id` =:a AND `option_id` =:o ORDER BY id DESC", nativeQuery = true)
    List<Appartenance> findByOptionId (@Param("a") Long annee,@Param("o") Long optionId);
    @Query(value = "SELECT * FROM `tb_appartenances` WHERE `etudiant_id` =:e ORDER BY id DESC", nativeQuery = true)
    List<Appartenance> findByEtudiantId (@Param("e") Long EtdId);
    @Query(value = "SELECT * FROM `tb_appartenances` WHERE `etudiant_id` =:e AND `annee_academique_id` =:a", nativeQuery = true)
    Appartenance findByEtudiantIdAndAnneeId (@Param("e") Long EtdId,@Param("a") Long AnId);
}
