package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query(value = "SELECT n FROM Note n INNER JOIN n.etudiant e INNER JOIN n.matiere.option o WHERE e.matricule =:m AND o.id =:o ORDER BY n.id DESC")
    List<Note> findNotes (@Param("m") String matricule,@Param("o")Long optionId);
    @Query(value = "SELECT n FROM Note n WHERE n.matiere.option.id =:i")
    List<Note> getOptionMarks(@Param("i") Long optionId);
    @Query(value = "SELECT n FROM Note n WHERE n.anneeAcademique.ecole.id =:i")
    List<Note> findByEcole(@Param("i") Long ecoleId);
    @Query(value = "SELECT n FROM Note n WHERE n.matiere.id =:m")
    List<Note> getMarksOfSubject(@Param("m") Long matiereId);
    @Query(value = "SELECT n FROM Note n WHERE n.etudiant.id =:e AND n.matiere.id =:m AND n.periode =:p")
    Optional<Note> getExistingMark(@Param("e") Long etudiantId, @Param("m") Long matiereId,@Param("p") String periode);

}



