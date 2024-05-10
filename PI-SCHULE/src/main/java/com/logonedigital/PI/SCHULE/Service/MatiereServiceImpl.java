package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.Enseignant;
import com.logonedigital.PI.SCHULE.Entity.Filiere;
import com.logonedigital.PI.SCHULE.Entity.Matiere;
import com.logonedigital.PI.SCHULE.Entity.Option;
import com.logonedigital.PI.SCHULE.Exception.RessourceExistException;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.MatiereMapper;
import com.logonedigital.PI.SCHULE.Repository.EnseignantRepository;
import com.logonedigital.PI.SCHULE.Repository.MatiereRepository;
import com.logonedigital.PI.SCHULE.Repository.OptionRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.IMatiereService;
import com.logonedigital.PI.SCHULE.dto.matiere_dto.MatiereRequest;
import com.logonedigital.PI.SCHULE.dto.matiere_dto.MatiereResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatiereServiceImpl implements IMatiereService {

    private final MatiereRepository matiereRepo;
    private final OptionRepository optionRepo;
    private final MatiereMapper matiereMapper;
    private final EnseignantRepository enseignantRepo;
    @Override
    public MatiereResponse addMatiere(MatiereRequest matiereRequest) {
        Matiere matiere = this.matiereMapper.fromMatiereRequest(matiereRequest);
        Option option = this.optionRepo.findById(matiereRequest.getOptionId())
                .orElseThrow(()-> new RessourceNotFoundException("Option not found"));
        matiere.setOption(option);
        Optional<Matiere> matiereAsked = this.matiereRepo.getMatire(
                option.getClasse()
                        .getFiliere()
                        .getCycle()
                        .getEcole()
                        .getId(),
                matiereRequest.getIntitule());
        if (matiereAsked.isPresent()){
            throw new RessourceExistException("Matiere already exist !!!");
        }
        if(matiereRequest.getEnseignantId() == null){
            matiere.setEnseignant(null);
        }else{
            Enseignant enseignant = this.enseignantRepo.findById(matiereRequest.getEnseignantId())
                    .orElseThrow(()-> new RessourceNotFoundException("Enseignant not found"));
            matiere.setEnseignant(enseignant);
        }
        return this.matiereMapper.fromMatiere(this.matiereRepo.save(matiere));
    }

    @Override
    public List<MatiereResponse> getMatiere() {
        List<Matiere> matieres = this.matiereRepo.findAll();
        List<MatiereResponse> matiereResponses = new ArrayList<>();
        matieres.forEach(matiere -> matiereResponses.add(this.matiereMapper.fromMatiere(matiere)));
        return matiereResponses;
    }

    @Override
    public List<MatiereResponse> getMatiereByEcole(Long ecoleId) {
        List<Matiere> matieres = this.matiereRepo.findAllByEcole(ecoleId);
        List<MatiereResponse> matiereResponses = new ArrayList<>();
        matieres.forEach(matiere -> matiereResponses.add(this.matiereMapper.fromMatiere(matiere)));
        return matiereResponses;
    }

    @Override
    public List<MatiereResponse> getMatiereByOption(Long id) {
        List<Matiere> matieres = this.matiereRepo.findByOption(id);
        List<MatiereResponse> matiereResponses = new ArrayList<>();
        matieres.forEach(matiere -> matiereResponses.add(this.matiereMapper.fromMatiere(matiere)));
        return matiereResponses;
    }

    @Override
    public MatiereResponse updateMatiere(Long id, MatiereRequest matiereRequest)throws RessourceNotFoundException {
        try {
            Matiere newMatiere = this.matiereRepo.findById(id).get();
            Option option = this.optionRepo.findById(matiereRequest.getOptionId())
                    .orElseThrow(() -> new RessourceNotFoundException("Option not found"));
            newMatiere.setOption(option);
            Optional<Matiere> matiereAsked = this.matiereRepo.getMatire(
                    option.getClasse()
                            .getFiliere()
                            .getCycle()
                            .getEcole()
                            .getId(),
                    matiereRequest.getIntitule());
            if (matiereAsked.isPresent()){
                throw new RessourceExistException("Matiere already exist !!!");
            }
            newMatiere.setCode(matiereRequest.getCode());
            newMatiere.setCoefficient(matiereRequest.getCoefficient());
            newMatiere.setIntitule(matiereRequest.getIntitule());
            newMatiere.setModule(matiereRequest.getModule());
            if(matiereRequest.getEnseignantId() == null){
                newMatiere.setEnseignant(null);
            }else{
                Enseignant enseignant = this.enseignantRepo.findById(matiereRequest.getEnseignantId())
                        .orElseThrow(()-> new RessourceNotFoundException("Enseignant not found"));
                newMatiere.setEnseignant(enseignant);
            }
            return this.matiereMapper.fromMatiere(this.matiereRepo.saveAndFlush(newMatiere));
        }catch (NoSuchElementException e){
            throw new RessourceNotFoundException("This matiere doesn't exist");
        }
    }
    @Override
    public MatiereResponse deleteMatiere(Long id)throws RessourceNotFoundException {
        try {
            Matiere newMatiere = this.matiereRepo.findById(id).get();
            newMatiere.setDeleted(true);
            return this.matiereMapper.fromMatiere(this.matiereRepo.saveAndFlush(newMatiere));
        }catch (NoSuchElementException e){
            throw new RessourceNotFoundException("This matiere can't be deleted");
        }
    }
}
