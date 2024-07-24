package com.genus.GENUS_PRIMO.Service;

import com.genus.GENUS_PRIMO.Entity.AnneeAcademique;
import com.genus.GENUS_PRIMO.Entity.Devoir;
import com.genus.GENUS_PRIMO.Entity.Matiere;
import com.genus.GENUS_PRIMO.Mapper.DevoirMapper;
import com.genus.GENUS_PRIMO.dto.devoir_dto.DevoirRequest;
import com.genus.GENUS_PRIMO.dto.devoir_dto.DevoirResponse;
import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Repository.AnneeAcademiqueRepository;
import com.genus.GENUS_PRIMO.Repository.DevoirRepository;
import com.genus.GENUS_PRIMO.Repository.MatiereRepository;
import com.genus.GENUS_PRIMO.Service.Interface.IDevoirService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DevoirServiceImpl implements IDevoirService {
    private final DevoirMapper devoirMapper;
    private final DevoirRepository devoirRepo;
    private final AnneeAcademiqueRepository anneeAcademiqueRepo;
    private final MatiereRepository matiereRepo;

    @Override
    public DevoirResponse addDevoir(DevoirRequest devoirRequest) {
        Devoir devoir = this.devoirMapper.fromDevoirRequest(devoirRequest);
        Matiere matiere = this.matiereRepo.findById(devoirRequest.getMatiereId())
                .orElseThrow(()-> new RessourceNotFoundException("Matiere Not found"));
        devoir.setMatiere(matiere);
        AnneeAcademique anneeAcademique = this.anneeAcademiqueRepo.findById(devoirRequest.getAnneeAcademiqueId())
                .orElseThrow(()-> new RessourceNotFoundException("Annee academique Not found"));
        devoir.setAnneeAcademique(anneeAcademique);
        devoir.setCreatedAt(new Date());
        return this.devoirMapper.fromDevoir(this.devoirRepo.save(devoir));
    }

    @Override
    public DevoirResponse editDevoir(Long id,DevoirRequest devoirRequest) {
        try{
            Devoir devoir = this.devoirMapper.fromDevoirRequest(devoirRequest);
            Devoir newDevoir = this.devoirRepo.findById(id).get();
            newDevoir.setTitre(devoir.getTitre());
            newDevoir.setDescription(devoir.getDescription());
            newDevoir.setLien(devoir.getLien());
            newDevoir.setUpdatedAt(new Date());
            Matiere matiere = this.matiereRepo.findById(devoirRequest.getMatiereId())
                    .orElseThrow(()-> new RessourceNotFoundException("Matiere Not found"));
            newDevoir.setMatiere(matiere);
            AnneeAcademique anneeAcademique = this.anneeAcademiqueRepo.findById(devoirRequest.getAnneeAcademiqueId())
                    .orElseThrow(()-> new RessourceNotFoundException("Annee academique Not found"));
            newDevoir.setAnneeAcademique(anneeAcademique);
            return this.devoirMapper.fromDevoir(this.devoirRepo.save(newDevoir));
        }catch (NoSuchElementException ex){
            throw new RessourceNotFoundException("Resource not found");
        }
    }

    @Override
    public List<DevoirResponse> listDevoirByEtudiant(Long anneeId, Long optionId) {
        List<Devoir> devoirs = this.devoirRepo.findByEtudiant(anneeId,optionId);
        List<DevoirResponse> devoirResponses = new ArrayList<>();
        devoirs.forEach(devoir -> devoirResponses.add(this.devoirMapper.fromDevoir(devoir)));
        return devoirResponses;
    }

    @Override
    public List<DevoirResponse> listDevoirByOption(Long optionId) {
        List<Devoir> devoirs = this.devoirRepo.findByOption(optionId);
        List<DevoirResponse> devoirResponses = new ArrayList<>();
        devoirs.forEach(devoir -> devoirResponses.add(this.devoirMapper.fromDevoir(devoir)));
        return devoirResponses;
    }
}
