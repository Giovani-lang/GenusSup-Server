package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.AnneeAcademique;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnneeAcademiqueRepository extends JpaRepository<AnneeAcademique,Long> {
    @Query(value = "SELECT a FROM AnneeAcademique a WHERE ecole.id =:e")
    List<AnneeAcademique> findByEcole(@Param("e")Long ecoleId);
    @Query(value = "SELECT a.annees FROM AnneeAcademique a WHERE a.ecole.id =:e AND a.annees =:a")
    Optional<AnneeAcademique> findAnneeByEcole(@Param("e")Long ecoleId,@Param("a")String annees);

    @Query(value = "SELECT a FROM AnneeAcademique a WHERE ecole.id =:e AND a.annees =:an")
    Optional<AnneeAcademique> getAnnee(@Param("e")Long ecoleId,@Param("an")String annees);
}
