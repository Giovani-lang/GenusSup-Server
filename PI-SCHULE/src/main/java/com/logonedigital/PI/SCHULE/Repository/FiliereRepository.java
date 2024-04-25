package com.logonedigital.PI.SCHULE.Repository;

import com.logonedigital.PI.SCHULE.Entity.Cycle;
import com.logonedigital.PI.SCHULE.Entity.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere,Long> {
    Optional<Filiere> findByNom (String nom);

    @Query(value = "SELECT * FROM `tb_filieres` WHERE `cycle_id` =:c", nativeQuery = true)
    List<Filiere> findByCycle (@Param("c") Long cycleId);

    @Query(value = "SELECT f FROM Filiere f WHERE f.cycle.ecole.id = :e")
    List<Filiere> findAllByEcole (@Param("e") Long ecoleId);
}
