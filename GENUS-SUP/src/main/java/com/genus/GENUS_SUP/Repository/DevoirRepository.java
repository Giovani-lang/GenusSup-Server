package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Devoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevoirRepository extends JpaRepository<Devoir, Long> {
    @Query(value = "SELECT d FROM Devoir d WHERE d.anneeAcademique.id =:a AND d.matiere.option.id =:o ORDER BY d.id DESC")
    List<Devoir> findByEtudiant (@Param("a")Long anneeId,@Param("o")Long optionId);
    @Query(value = "SELECT d FROM Devoir d WHERE d.matiere.option.id =:o ORDER BY d.id DESC")
    List<Devoir> findByOption (@Param("o")Long optionId);
}
