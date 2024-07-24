package com.genus.GENUS_PRIMO.Service;

import com.genus.GENUS_PRIMO.Entity.Classe;
import com.genus.GENUS_PRIMO.Entity.Cycle;
import com.genus.GENUS_PRIMO.Mapper.ClasseMapper;
import com.genus.GENUS_PRIMO.Repository.CycleRepository;
import com.genus.GENUS_PRIMO.dto.classe_dto.ClasseRequest;
import com.genus.GENUS_PRIMO.dto.classe_dto.ClasseResponse;
import com.genus.GENUS_PRIMO.Exception.RessourceExistException;
import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Repository.ClasseRepository;
import com.genus.GENUS_PRIMO.Service.Interface.IClasseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClasseServiceImpl implements IClasseService {

    private final ClasseRepository classeRepo;
    private final ClasseMapper classeMapper;
    private final CycleRepository cycleRepo;

    @Override
    public ClasseResponse addClasse(ClasseRequest classeRequest) {
        Classe classe = this.classeMapper.fromClasseRequest(classeRequest);
        Optional<Classe> classeAsked = this.classeRepo.getClasse(classeRequest.getCycleId(), classeRequest.getNom());
        if (classeAsked.isPresent()){
            throw new RessourceExistException("Classe already exist !!!");
        }
        Cycle cycle = this.cycleRepo.findById(classeRequest.getCycleId())
                .orElseThrow(() -> new RessourceNotFoundException("This cycle doesn't exist"));
        classe.setCycle(cycle);
        classe.setNom(classeRequest.getNom());
        classe.setNiveau(classeRequest.getNiveau());
        return this.classeMapper.fromClasse(this.classeRepo.save(classe)) ;
    }


    @Override
    public List<ClasseResponse> getClasse(Long ecoleId) {
        List<Classe> classe = this.classeRepo.findAllByEcole(ecoleId);
        List<ClasseResponse> classeResponses = new ArrayList<>();
        classe.forEach(classe1 -> classeResponses.add(this.classeMapper.fromClasse(classe1)));
        return classeResponses;
    }

    @Override
    public List<ClasseResponse> getClasseByFiliere(Long filiereId) {
        List<Classe> classe = this.classeRepo.findByCycle(filiereId);
        List<ClasseResponse> classeResponses = new ArrayList<>();
        classe.forEach(classe1 -> classeResponses.add(this.classeMapper.fromClasse(classe1)));
        return classeResponses;
    }

    @Override
    public ClasseResponse updateClasse(Long id, ClasseRequest classeRequest) throws RessourceNotFoundException {
       try {
           Classe newClasse = this.classeRepo.findById(id).get();
           Optional<Classe> classeAsked = this.classeRepo.getClasse(classeRequest.getCycleId(), classeRequest.getNom());
           if (classeAsked.isPresent() && newClasse.getNiveau() == (classeAsked.get().getNiveau()) ){
               throw new RessourceExistException("Cycle already exist !!!");
           }
           Cycle cycle = this.cycleRepo.findById(classeRequest.getCycleId())
                   .orElseThrow(() -> new RessourceNotFoundException("This cycle doesn't exist"));
           newClasse.setNom(classeRequest.getNom());
           newClasse.setCycle(cycle);
           newClasse.setNiveau(classeRequest.getNiveau());
           return this.classeMapper.fromClasse(this.classeRepo.saveAndFlush(newClasse));
       }catch (NoSuchElementException ex){
           throw new RessourceNotFoundException("Impossible to update this classe!");
       }
    }
    @Override
    public ClasseResponse deleteClasse(Long id) throws RessourceNotFoundException {
       try {
           Classe newClasse = this.classeRepo.findById(id).get();
           newClasse.setDeleted(true);
           return this.classeMapper.fromClasse(this.classeRepo.saveAndFlush(newClasse));
       }catch (NoSuchElementException ex){
           throw new RessourceNotFoundException("Impossible to delete this classe!");
       }
    }

}
