package com.logonedigital.PI.SCHULE.Repository;

import com.logonedigital.PI.SCHULE.Entity.Note;
import com.logonedigital.PI.SCHULE.dto.note_dto.NoteResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query(value = "SELECT n FROM Note n INNER JOIN n.etudiant e INNER JOIN n.matiere.option o WHERE e.matricule =:m AND o.id =:o ")
    List<Note> findNotes (@Param("m") String matricule,@Param("o")Long optionId);

@Query(value = "SELECT n FROM Note n INNER JOIN n.etudiant e INNER JOIN n.matiere.option o WHERE e.matricule =:m AND o.id =:o AND n.anneeAcademique.id =:a")
List<Note> findNotesByEtudiantAndYears (@Param("m") String matricule,@Param("o") Long optionId, @Param("a") Long anneeAcademiqueId);

}



