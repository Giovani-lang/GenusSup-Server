package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.Classe;
import com.logonedigital.PI.SCHULE.Entity.Filiere;
import com.logonedigital.PI.SCHULE.Entity.Option;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.OptionMapper;
import com.logonedigital.PI.SCHULE.Repository.ClasseRepository;
import com.logonedigital.PI.SCHULE.Repository.FiliereRepository;
import com.logonedigital.PI.SCHULE.Repository.OptionRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.IOptionService;
import com.logonedigital.PI.SCHULE.dto.option_dto.OptionRequest;
import com.logonedigital.PI.SCHULE.dto.option_dto.OptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements IOptionService {

    private final OptionRepository optionRepo;
    private final OptionMapper optionMapper;
    private final ClasseRepository classeRepo;
    @Override
    public OptionResponse addOption(OptionRequest optionRequest) {
        Option option = this.optionMapper.fromOptionRequest(optionRequest);
        Classe classe = this.classeRepo.findById(optionRequest.getClasseId())
                .orElseThrow(()-> new RessourceNotFoundException("Classe not found"));
        option.setClasse(classe);
        return this.optionMapper.fromOption(this.optionRepo.save(option));
    }

    @Override
    public List<OptionResponse> getOption(Long ecoleId) {
        List<Option> options = this.optionRepo.findAllByEcole(ecoleId);
        List<OptionResponse> optionResponses = new ArrayList<>();
        options.forEach(option -> optionResponses.add(this.optionMapper.fromOption(option)));
        return optionResponses;
    }

    @Override
    public List<OptionResponse> getOptionByClasse(Long classeId) {
        List<Option> options = this.optionRepo.findByClasse(classeId);
        List<OptionResponse> optionResponses = new ArrayList<>();
        options.forEach(option -> optionResponses.add(this.optionMapper.fromOption(option)));
        return optionResponses;
    }

    @Override
    public List<OptionResponse> getOptionForTeacher(String ensEmail) {
        List<Option> options = this.optionRepo.findAllByTeacher(ensEmail);
        List<OptionResponse> optionResponses = new ArrayList<>();
        options.forEach(option -> optionResponses.add(this.optionMapper.fromOption(option)));
        return optionResponses;
    }

    @Override
    public OptionResponse getById(Long id) {
        return this.optionMapper.fromOption(this.optionRepo.findById(id).get());
    }

    @Override
    public OptionResponse updateOption(Long id, OptionRequest optionRequest) {
        Option newOption = this.optionRepo.findById(id).get();
        newOption.setNom(optionRequest.getNom());
        Classe classe = this.classeRepo.findById(optionRequest.getClasseId())
                .orElseThrow(()-> new RessourceNotFoundException("Classe not found"));
        newOption.setClasse(classe);
        return this.optionMapper.fromOption(this.optionRepo.saveAndFlush(newOption));
    }
}
