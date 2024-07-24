package com.genus.GENUS_PRIMO.Repository;

import com.genus.GENUS_PRIMO.Entity.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    @Query(value = "SELECT c FROM Classe c WHERE c.cycle.id =:i AND c.isDeleted = 0")
    List<Classe> findByCycle (@Param("i") Long cycleId);

    @Query(value = "SELECT c FROM Classe c WHERE c.cycle.campus.ecole.id = :e AND c.isDeleted = 0")
    List<Classe> findAllByEcole (@Param("e") Long ecoleId);
    @Query(value = "SELECT c FROM Classe c WHERE c.cycle.id = :c AND c.nom =:n AND c.isDeleted = 0")
    Optional<Classe> getClasse (@Param("c") Long cycleId, @Param("n") String classe);
}
