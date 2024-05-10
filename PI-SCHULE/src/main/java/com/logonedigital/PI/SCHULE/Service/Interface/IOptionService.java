package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.Entity.Option;
import com.logonedigital.PI.SCHULE.dto.option_dto.OptionRequest;
import com.logonedigital.PI.SCHULE.dto.option_dto.OptionResponse;

import java.util.List;

public interface IOptionService {
    OptionResponse addOption (OptionRequest optionRequest);
    List<OptionResponse> getOption (Long ecoleId);
    List<OptionResponse> getOptionByClasse (Long classeId);
    List<OptionResponse> getOptionForTeacher (String ensEmail);
    OptionResponse updateOption (Long id,OptionRequest optionRequest);
    OptionResponse deleteOption (Long id);
}
