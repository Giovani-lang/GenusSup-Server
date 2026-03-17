package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarifRepository extends JpaRepository<Tarif, Long> {
    @Query(value = "SELECT t FROM Tarif t WHERE t.option.classe.filiere.cycle.departement.campus.ecole.id = :e AND t.isDeleted = 0 ORDER BY t.id DESC")
    List<Tarif> findAllByEcole (@Param("e") Long ecoleId);
    @Query(value = "SELECT t FROM Tarif t WHERE t.option.id = :o AND t.isDeleted = 0")
    Optional<Tarif> getTarif (@Param("o") Long optionId);
}
