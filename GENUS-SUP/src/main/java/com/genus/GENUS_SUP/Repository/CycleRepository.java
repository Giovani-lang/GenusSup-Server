package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CycleRepository extends JpaRepository<Cycle,Long> {
    @Query(value = "SELECT c FROM Cycle c WHERE c.departement.campus.ecole.id = :e AND c.isDeleted = 0 ORDER BY c.id DESC")
    List<Cycle> findAllByEcole (@Param("e") Long ecoleId);
    @Query(value = "SELECT c FROM Cycle c WHERE c.departement.id =:d AND c.nom =:n AND c.isDeleted = 0")
    Optional<Cycle> getCycle (@Param("d") Long departementId, @Param("n") String cycle);
}
