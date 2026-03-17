package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Long> {
    @Query(value = "SELECT b FROM Bulletin b WHERE b.enseignant.id =:i ORDER BY b.id DESC")
    List<Bulletin> findBulletinByEnseignant(@Param("i") Long enseignantId);
    @Query(value = "SELECT b FROM Bulletin b WHERE b.enseignant.ecole.id =:i ORDER BY b.id DESC")
    List<Bulletin> findBulletinByEcole(@Param("i") Long ecoleId);
}
