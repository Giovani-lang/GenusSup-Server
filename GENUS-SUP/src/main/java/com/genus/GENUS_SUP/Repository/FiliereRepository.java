package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    @Query(value = "SELECT f FROM Filiere f WHERE f.cycle.departement.campus.ecole.id = :e AND f.isDeleted = 0 ORDER BY f.id DESC")
    List<Filiere> findAllByEcole (@Param("e") Long ecoleId);
    @Query(value = "SELECT f FROM Filiere f WHERE f.cycle.id = :e AND f.nom =:n AND f.isDeleted = 0")
    Optional<Filiere> getFiliere (@Param("e") Long cycleId, @Param("n") String filiere);
}
