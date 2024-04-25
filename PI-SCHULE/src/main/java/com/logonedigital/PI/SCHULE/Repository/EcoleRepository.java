package com.logonedigital.PI.SCHULE.Repository;

import com.logonedigital.PI.SCHULE.Entity.Ecole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EcoleRepository extends JpaRepository<Ecole,Long> {
    Optional<Ecole> findByName(String name);
    Optional<Ecole> findByPhone(String name);
    Optional<Ecole> findByFax(String name);
    Optional<Ecole> findByEmail(String email);
}
