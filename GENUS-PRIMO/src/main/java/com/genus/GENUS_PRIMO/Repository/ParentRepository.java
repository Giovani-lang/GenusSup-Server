package com.genus.GENUS_PRIMO.Repository;

import com.genus.GENUS_PRIMO.Entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ParentRepository extends JpaRepository<Parent,Long> {
    Optional<Parent> findByEmail(String email);
    Optional<Parent> findByTelephone(String telephone);

    @Query(value = "SELECT p FROM Parent p WHERE p.etudiant.ecole.id = :e")
    List<Parent> search (@Param("e") Long ecoleId);
}
