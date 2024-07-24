package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.option_dto.OptionRequest;
import com.genus.GENUS_PRIMO.dto.option_dto.OptionResponse;

import java.util.List;

public interface IOptionService {
    OptionResponse addOption (OptionRequest optionRequest);
    List<OptionResponse> getOption (Long ecoleId);
    List<OptionResponse> getOptionByClasse (Long classeId);
    List<OptionResponse> getOptionForTeacher (String ensEmail);
    OptionResponse updateOption (Long id,OptionRequest optionRequest);
    OptionResponse deleteOption (Long id);
}
