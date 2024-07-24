package com.genus.GENUS_PRIMO.Service;

import com.genus.GENUS_PRIMO.Entity.AnneeAcademique;
import com.genus.GENUS_PRIMO.Entity.Ecole;
import com.genus.GENUS_PRIMO.Mapper.AnneeAcademiqueMapper;
import com.genus.GENUS_PRIMO.dto.anneeAcademique_dto.AnneeAcademiqueRequest;
import com.genus.GENUS_PRIMO.dto.anneeAcademique_dto.AnneeAcademiqueResponse;
import com.genus.GENUS_PRIMO.Exception.RessourceExistException;
import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Repository.AnneeAcademiqueRepository;
import com.genus.GENUS_PRIMO.Repository.EcoleRepository;
import com.genus.GENUS_PRIMO.Service.Interface.IAnneeAcademiqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnneeAcademiqueServiceImpl implements IAnneeAcademiqueService {
    private final AnneeAcademiqueRepository anneeAcademiqueRepo;
    private final AnneeAcademiqueMapper anneeAcademiqueMapper;
    private final EcoleRepository ecoleRepo;
    @Override
    public AnneeAcademiqueResponse addAnnee(AnneeAcademiqueRequest anneeAcademiqueRequest) {
        AnneeAcademique anneeAcademique = this.anneeAcademiqueMapper.fromAnneeAcademiqueRequest(anneeAcademiqueRequest);
        Optional<AnneeAcademique> anneeAsked = this.anneeAcademiqueRepo.getAnnee(
                anneeAcademiqueRequest.getEcoleId(),
                anneeAcademique.getAnnees()
        );
        if (anneeAsked.isPresent()){
            throw new RessourceExistException("Annee already exist !!!");
        }
        Optional<AnneeAcademique> annee = this.anneeAcademiqueRepo.findAnneeByEcole(anneeAcademiqueRequest.getEcoleId(),
                anneeAcademiqueRequest.getAnnees());
        if (annee.isPresent()){
            throw new RessourceExistException("This years already exist !!!");
        }
        Ecole ecole = this.ecoleRepo.findById(anneeAcademiqueRequest.getEcoleId())
                .orElseThrow(()-> new RessourceNotFoundException("School"+anneeAcademiqueRequest.getEcoleId()+"doesn't exsit" ));
        anneeAcademique.setEcole(ecole);
        return this.anneeAcademiqueMapper.fromAnneeAcademique(this.anneeAcademiqueRepo.save(anneeAcademique));
    }

    @Override
    public AnneeAcademiqueResponse getAnnee(Long id) throws RessourceNotFoundException {
       try {
           return this.anneeAcademiqueMapper.fromAnneeAcademique(this.anneeAcademiqueRepo.findById(id).get());
       }catch (NoSuchElementException ex){
           throw new RessourceNotFoundException("Doesn't exist !");
       }
    }

    @Override
    public List<AnneeAcademiqueResponse> getAllAnnee(Long ecoleId) {
        List<AnneeAcademique> anneeAcademiques = this.anneeAcademiqueRepo.findByEcole(ecoleId);
        List<AnneeAcademiqueResponse> anneeAcademiqueResponses = new ArrayList<>();
        anneeAcademiques.forEach(anneeAcademique -> anneeAcademiqueResponses
                .add(this.anneeAcademiqueMapper.fromAnneeAcademique(anneeAcademique)));
        return anneeAcademiqueResponses;
    }

    @Override
    public AnneeAcademiqueResponse editAnnee(Long id, AnneeAcademiqueRequest anneeAcademiqueRequest)throws RessourceNotFoundException {
       try {
           AnneeAcademique anneeAcademiqueUpdated = this.anneeAcademiqueRepo.findById(id).get();
           Optional<AnneeAcademique> anneeAsked = this.anneeAcademiqueRepo.getAnnee(
                   anneeAcademiqueRequest.getEcoleId(),
                   anneeAcademiqueRequest.getAnnees()
           );
           if (anneeAsked.isPresent()){
               throw new RessourceExistException("Annee already exist !!!");
           }
           AnneeAcademique anneeAcademique = this.anneeAcademiqueMapper.fromAnneeAcademiqueRequest(anneeAcademiqueRequest);
           anneeAcademiqueUpdated.setAnnees(anneeAcademique.getAnnees());
           return this.anneeAcademiqueMapper.fromAnneeAcademique(this.anneeAcademiqueRepo.saveAndFlush(anneeAcademiqueUpdated));
       }catch (NoSuchElementException ex){
           throw new RessourceNotFoundException("Doesn't exist !");
       }
    }

}
