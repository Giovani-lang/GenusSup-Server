package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {
    Optional<Enseignant> findByEmail(String email);
    Optional<Enseignant> findByTelephone(String telephone);
    @Query(value = "SELECT e FROM Enseignant e WHERE e.ecole.id = :e ORDER BY e.id DESC")
    List<Enseignant> findAllByEcole (@Param("e") Long ecoleId);

}
