package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    @Query(value = "SELECT m FROM Matiere m WHERE m.option.classe.filiere.cycle.departement.campus.ecole.id = :e AND m.isDeleted = 0 ORDER BY m.id DESC")
    List<Matiere> findAllByEcole (@Param("e") Long ecoleId);

    @Query(value = "SELECT m FROM Matiere m WHERE m.option.id =:o AND m.isDeleted = 0 ORDER BY m.id DESC")
    List<Matiere> findByOption (@Param("o") Long id);

    @Query(value = "SELECT m FROM Matiere m WHERE m.option.classe.filiere.cycle.departement.campus.ecole.id = :e AND m.intitule =:i AND m.isDeleted = 0")
    Optional<Matiere> getMatire (@Param("e") Long ecoleId,@Param("i") String intitule);
}
