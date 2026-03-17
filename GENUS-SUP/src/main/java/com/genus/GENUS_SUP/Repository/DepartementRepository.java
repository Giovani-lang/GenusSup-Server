package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {
    @Query(value = "SELECT d FROM Departement d WHERE d.campus.ecole.id = :e AND d.isDeleted = 0 ORDER BY d.id DESC")
    List<Departement> findAllByEcole (@Param("e") Long ecoleId);
    @Query(value = "SELECT d FROM Departement d WHERE d.campus.id =:c AND d.nom =:n AND d.isDeleted = 0")
    Optional<Departement> getDepartement (@Param("c") Long campusId, @Param("n") String departement);
}
