package com.logonedigital.PI.SCHULE.Repository;

import com.logonedigital.PI.SCHULE.Entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin,Long> {
    Optional<SuperAdmin> findByTelephone(String phone);
    Optional<SuperAdmin> findByEmail(String email);
    @Query(value = "SELECT COUNT(*) AS user_count FROM `tb_super_admin`", nativeQuery = true)
    Long countBySuperAdmin ();
}
