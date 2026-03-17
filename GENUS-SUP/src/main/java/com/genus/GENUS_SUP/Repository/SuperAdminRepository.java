package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin,Long> {
    Optional<SuperAdmin> findByTelephone(String phone);
    Optional<SuperAdmin> findByEmail(String email);
    @Query(value = "SELECT a FROM SuperAdmin a WHERE a.ecole.id = :e")
    List<SuperAdmin> findAllByEcole (@Param("e") Long ecoleId);
}
