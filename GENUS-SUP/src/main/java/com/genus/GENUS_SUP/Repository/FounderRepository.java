package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Founder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FounderRepository extends JpaRepository<Founder,Long> {
    Optional<Founder> findByTelephone(String phone);
    Optional<Founder> findByEmail(String email);
    @Query(value = "SELECT COUNT(*) AS user_count FROM `tb_saas_admin` ORDER BY id DESC", nativeQuery = true)
    Long countByFounder ();
}
