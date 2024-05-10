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
    @Query(value = "SELECT * FROM `tb_classes` WHERE `filiere_id` =:f AND `is_deleted` = 0", nativeQuery = true)
    List<Classe> findByFiliere (@Param("f") Long filiereId);

    @Query(value = "SELECT c FROM Classe c WHERE c.filiere.cycle.ecole.id = :e AND c.isDeleted = 0")
    List<Classe> findAllByEcole (@Param("e") Long ecoleId);
    @Query(value = "SELECT c FROM Classe c WHERE c.filiere.id = :e AND c.nom =:n AND c.isDeleted = 0")
    Optional<Classe> getClasse (@Param("e") Long filiereId, @Param("n") String classe);
}
