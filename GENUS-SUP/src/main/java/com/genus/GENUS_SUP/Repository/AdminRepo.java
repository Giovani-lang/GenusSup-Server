package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Administration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Administration, Long>{
    Optional<Administration>findByEmail(String email);
    Optional<Administration>findByTelephone(String telephone);
    @Query(value = "SELECT a FROM Administration a WHERE a.ecole.id = :e")
    List<Administration> findAllByEcole (@Param("e") Long ecoleId);

}
