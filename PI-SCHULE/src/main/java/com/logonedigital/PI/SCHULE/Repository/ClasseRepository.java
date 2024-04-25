package com.logonedigital.PI.SCHULE.Repository;

import com.logonedigital.PI.SCHULE.Entity.Classe;
import com.logonedigital.PI.SCHULE.Entity.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    Optional<Classe> findByNom (String nom);

    @Query(value = "SELECT * FROM `tb_classes` WHERE `filiere_id` =:f", nativeQuery = true)
    List<Classe> findByFiliere (@Param("f") Long filiereId);

    @Query(value = "SELECT c FROM Classe c WHERE c.filiere.cycle.ecole.id = :e")
    List<Classe> findAllByEcole (@Param("e") Long ecoleId);
}
