package com.genus.GENUS_PRIMO.Repository;

import com.genus.GENUS_PRIMO.Entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CampusRepository extends JpaRepository<Campus,Long> {
    @Query(value = "SELECT c FROM Campus c WHERE c.ecole.id = :e AND c.isDeleted = 0")
    List<Campus> searchBySchool (@Param("e") Long ecoleId);
    @Query(value = "SELECT c FROM Campus c WHERE c.ecole.id = :e AND c.name =:n AND c.isDeleted = 0")
    Optional<Campus> getCampus (@Param("e") Long ecoleId, @Param("n") String campus);
}
