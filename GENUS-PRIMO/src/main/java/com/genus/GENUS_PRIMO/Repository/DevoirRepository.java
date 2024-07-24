package com.genus.GENUS_PRIMO.Repository;

import com.genus.GENUS_PRIMO.Entity.Devoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevoirRepository extends JpaRepository<Devoir, Long> {
    @Query(value = "SELECT d FROM Devoir d WHERE d.anneeAcademique.id =:a AND d.matiere.option.id =:o")
    List<Devoir> findByEtudiant (@Param("a")Long anneeId,@Param("o")Long optionId);
    @Query(value = "SELECT d FROM Devoir d WHERE d.matiere.option.id =:o")
    List<Devoir> findByOption (@Param("o")Long optionId);
}
