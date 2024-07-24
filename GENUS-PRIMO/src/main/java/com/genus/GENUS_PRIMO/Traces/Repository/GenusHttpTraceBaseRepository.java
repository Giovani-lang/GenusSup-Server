package com.genus.GENUS_PRIMO.Traces.Repository;

import com.genus.GENUS_PRIMO.Traces.Model.GenusHttpTrace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenusHttpTraceBaseRepository extends JpaRepository<GenusHttpTrace, Long> {
    @Query(value="SELECT g FROM GenusHttpTrace g WHERE str(g.timestamp) LIKE %:searchTerm% OR str(g.principal) LIKE %:searchTerm% OR str(g.apiPath) LIKE %:searchTerm% OR str(g.session) LIKE %:searchTerm% OR str(g.request) LIKE %:searchTerm%  OR str(g.response) LIKE %:searchTerm%")
    Page<GenusHttpTrace> findAllBySearchTerm(String searchTerm, Pageable pageable);
}
