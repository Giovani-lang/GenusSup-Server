package com.logonedigital.PI.SCHULE.Repository;

import com.logonedigital.PI.SCHULE.Entity.Classe;
import com.logonedigital.PI.SCHULE.Entity.Filiere;
import com.logonedigital.PI.SCHULE.Entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    Optional<Option> findByNom(String nom);

    @Query(value = "SELECT * FROM `tb_options` WHERE `classe_id` =:c", nativeQuery = true)
    List<Option> findByClasse(@Param("c") Long classeId);

    @Query(value = "SELECT o FROM Option o WHERE o.classe.filiere.cycle.ecole.id = :e")
    List<Option> findAllByEcole (@Param("e") Long ecoleId);
    @Query("SELECT o FROM Option o JOIN o.matieres m WHERE m.enseignant.email = :em")
    List<Option> findAllByTeacher (@Param("em") String ensEmail);
}
