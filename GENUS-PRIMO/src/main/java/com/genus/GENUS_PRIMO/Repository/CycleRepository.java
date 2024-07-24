package com.genus.GENUS_PRIMO.Repository;

import com.genus.GENUS_PRIMO.Entity.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CycleRepository extends JpaRepository<Cycle,Long> {
    @Query(value = "SELECT c FROM Cycle c WHERE c.campus.ecole.id = :e AND c.isDeleted = 0")
    List<Cycle> findAllByEcole (@Param("e") Long ecoleId);
    @Query(value = "SELECT c FROM Cycle c WHERE c.campus.id = :e AND c.nom =:n AND c.isDeleted = 0")
    Optional<Cycle> getCycle (@Param("e") Long campusId, @Param("n") String cycle);
}
