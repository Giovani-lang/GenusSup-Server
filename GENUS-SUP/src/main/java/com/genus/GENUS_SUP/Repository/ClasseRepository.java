package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    @Query(value = "SELECT c FROM Classe c WHERE c.filiere.id =:f AND c.isDeleted = 0 ORDER BY c.id DESC")
    List<Classe> findByFiliere (@Param("f") Long filiereId);

    @Query(value = "SELECT c FROM Classe c WHERE c.filiere.cycle.departement.campus.ecole.id =:e AND c.isDeleted = 0 ORDER BY c.id DESC")
    List<Classe> findAllByEcole (@Param("e") Long ecoleId);
    @Query(value = "SELECT c FROM Classe c WHERE c.filiere.id =:f AND c.nom =:n AND c.isDeleted = 0")
    Optional<Classe> getClasse (@Param("f") Long filiereId, @Param("n") String classe);
}
