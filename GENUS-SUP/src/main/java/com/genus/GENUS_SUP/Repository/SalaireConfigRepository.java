package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.SalaireConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaireConfigRepository extends JpaRepository<SalaireConfig, Long> {
    SalaireConfig findByEcoleId(Long ecoleId);
}
