package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.Classe;
import com.logonedigital.PI.SCHULE.Entity.Filiere;
import com.logonedigital.PI.SCHULE.Exception.RessourceExistException;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.ClasseMapper;
import com.logonedigital.PI.SCHULE.Repository.ClasseRepository;
import com.logonedigital.PI.SCHULE.Repository.FiliereRepository;
import com.logonedigital.PI.SCHULE.Repository.OptionRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.IClasseService;
import com.logonedigital.PI.SCHULE.dto.classe_dto.ClasseRequest;
import com.logonedigital.PI.SCHULE.dto.classe_dto.ClasseResponse;
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
    private final FiliereRepository filiereRepo;

    @Override
    public ClasseResponse addClasse(ClasseRequest classeRequest) {
        Classe classe = this.classeMapper.fromClasseRequest(classeRequest);
        Optional<Classe> classeAsked = this.classeRepo.getClasse(classeRequest.getFiliereId(), classeRequest.getNom());
        if (classeAsked.isPresent()){
            throw new RessourceExistException("Classe already exist !!!");
        }
        Filiere filiere = this.filiereRepo.findById(classeRequest.getFiliereId())
                .orElseThrow(() -> new RessourceNotFoundException("This filiere doesn't exist"));
        classe.setFiliere(filiere);
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
        List<Classe> classe = this.classeRepo.findByFiliere(filiereId);
        List<ClasseResponse> classeResponses = new ArrayList<>();
        classe.forEach(classe1 -> classeResponses.add(this.classeMapper.fromClasse(classe1)));
        return classeResponses;
    }

    @Override
    public ClasseResponse updateClasse(Long id, ClasseRequest classeRequest) throws RessourceNotFoundException {
       try {
           Classe newClasse = this.classeRepo.findById(id).get();
           Optional<Classe> classeAsked = this.classeRepo.getClasse(classeRequest.getFiliereId(), classeRequest.getNom());
           if (classeAsked.isPresent() && newClasse.getNiveau().equals(classeAsked.get().getNiveau()) ){
               throw new RessourceExistException("Classe already exist !!!");
           }
           Filiere filiere = this.filiereRepo.findById(classeRequest.getFiliereId())
                   .orElseThrow(() -> new RessourceNotFoundException("This filiere doesn't exist"));
           newClasse.setNom(classeRequest.getNom());
           newClasse.setFiliere(filiere);
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
