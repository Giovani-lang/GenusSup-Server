package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Emargement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmargementRepository extends JpaRepository<Emargement,Long> {
    @Query(value = "SELECT e FROM Emargement e WHERE e.enseignant.email =:m ORDER BY e.id DESC")
    List<Emargement> findByEnseignant (@Param("m")String email);
    @Query(value = "SELECT e FROM Emargement e WHERE e.enseignant.ecole.id =:i ORDER BY e.id DESC")
    List<Emargement> AllEmargements (@Param("i")Long ecoleId);
    @Query(value = "SELECT e FROM Emargement e  WHERE e.planification.id =:i")
    Optional<Emargement> findByPlanning(@Param("i") Long planificationId);

    @Query("SELECT e FROM Emargement e WHERE e.enseignant.id = :id AND e.planification.Jour BETWEEN :startDate AND :endDate")
    List<Emargement> findByPlanificationDateBetween(
            @Param("id") Long id,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

}
