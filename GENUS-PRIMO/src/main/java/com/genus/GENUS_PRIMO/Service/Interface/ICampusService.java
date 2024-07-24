package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.campus_dto.CampusRequest;
import com.genus.GENUS_PRIMO.dto.campus_dto.CampusResponse;

import java.util.List;

public interface ICampusService {
    CampusResponse addCampus(CampusRequest campusRequest);
    List<CampusResponse> getCampus(Long id);
    CampusResponse editCampus (Long id, CampusRequest campusRequest);
    CampusResponse deleteCampus (Long id);
}
