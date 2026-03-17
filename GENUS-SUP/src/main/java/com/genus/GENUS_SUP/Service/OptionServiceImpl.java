package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Classe;
import com.genus.GENUS_SUP.Entity.Option;
import com.genus.GENUS_SUP.Mapper.OptionMapper;
import com.genus.GENUS_SUP.dto.option_dto.OptionRequest;
import com.genus.GENUS_SUP.dto.option_dto.OptionResponse;
import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Repository.ClasseRepository;
import com.genus.GENUS_SUP.Repository.OptionRepository;
import com.genus.GENUS_SUP.Service.Interface.IOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements IOptionService {

    private final OptionRepository optionRepo;
    private final OptionMapper optionMapper;
    private final ClasseRepository classeRepo;
    @Override
    public OptionResponse addOption(OptionRequest optionRequest) {
        Option option = this.optionMapper.fromOptionRequest(optionRequest);
        Optional<Option> filiereAsked = this.optionRepo.getOption(optionRequest.getClasseId(), optionRequest.getNom());
        if (filiereAsked.isPresent()){
            throw new RessourceExistException("Option already exist !!!");
        }
        Classe classe = this.classeRepo.findById(optionRequest.getClasseId())
                .orElseThrow(()-> new RessourceNotFoundException("Classe not found"));
        option.setClasse(classe);
        return this.optionMapper.fromOption(this.optionRepo.save(option));
    }

    @Override
    public OptionResponse getOption(Long id) {
        return this.optionMapper.fromOption(this.optionRepo.findById(id)
                .orElseThrow(()->new RessourceNotFoundException("Not found"))
        );
    }

    @Override
    public List<OptionResponse> getOptionByEcole(Long ecoleId) {
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
    public OptionResponse updateOption(Long id, OptionRequest optionRequest) {
        Option newOption = this.optionRepo.findById(id).get();
        newOption.setNom(optionRequest.getNom());
        Classe classe = this.classeRepo.findById(optionRequest.getClasseId())
                .orElseThrow(()-> new RessourceNotFoundException("Classe not found"));
        newOption.setClasse(classe);
        return this.optionMapper.fromOption(this.optionRepo.saveAndFlush(newOption));
    }
    @Override
    public OptionResponse deleteOption(Long id) {
        Option newOption = this.optionRepo.findById(id).get();
        newOption.setDeleted(true);
        return this.optionMapper.fromOption(this.optionRepo.saveAndFlush(newOption));
    }
}
