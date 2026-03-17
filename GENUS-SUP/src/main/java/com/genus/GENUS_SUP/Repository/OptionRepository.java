package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    @Query(value = "SELECT * FROM `tb_options` WHERE `classe_id` =:c AND `is_deleted` = 0 ORDER BY id DESC", nativeQuery = true)
    List<Option> findByClasse(@Param("c") Long classeId);

    @Query(value = "SELECT o FROM Option o WHERE o.classe.filiere.cycle.departement.campus.ecole.id = :e AND o.isDeleted = 0 ORDER BY o.id DESC")
    List<Option> findAllByEcole (@Param("e") Long ecoleId);

    @Query("SELECT o FROM Option o JOIN o.matieres m WHERE m.enseignant.email = :em AND o.isDeleted = 0 ORDER BY o.id DESC")
    List<Option> findAllByTeacher (@Param("em") String ensEmail);

    @Query(value = "SELECT o FROM Option o WHERE o.classe.id = :e AND o.nom =:n AND o.isDeleted = 0")
    Optional<Option> getOption (@Param("e") Long classeId, @Param("n") String option);
}
