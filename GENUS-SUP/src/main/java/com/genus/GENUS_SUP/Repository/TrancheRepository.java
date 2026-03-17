package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Tranche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrancheRepository extends JpaRepository<Tranche, Long> {
    @Query(value = "SELECT t FROM Tranche t WHERE t.tarif.id =:a ORDER BY t.id DESC")
    List<Tranche> getAllTrancheByTariff (@Param("a")Long id);

    @Query(value = "SELECT t FROM Tranche t WHERE t.tarif.id =:i AND t.numero =:n")
    Optional<Tranche> findByNumero(@Param("i")Long tarifId,@Param("n")Long numero);
}
