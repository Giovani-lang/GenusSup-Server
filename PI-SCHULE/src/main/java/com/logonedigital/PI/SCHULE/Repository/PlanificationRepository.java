package com.logonedigital.PI.SCHULE.Repository;

import com.logonedigital.PI.SCHULE.Entity.Planification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanificationRepository extends JpaRepository<Planification,Long> {
    @Query(value = "SELECT p FROM Planification p WHERE p.matiere.option.classe.filiere.cycle.ecole.id =:e AND p.matiere.option.id =:o")
    List<Planification> findByOption(@Param("e") Long ecoleId,@Param("o") Long optionId);
    @Query(value = "SELECT p FROM Planification p WHERE p.matiere.option.classe.filiere.cycle.ecole.id =:e AND p.matiere.enseignant.email =:em")
    List<Planification> findByTeacher(@Param("e") Long ecoleId,@Param("em") String ensEmail);
}
