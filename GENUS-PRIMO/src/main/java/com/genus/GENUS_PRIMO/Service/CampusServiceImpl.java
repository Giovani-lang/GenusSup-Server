package com.genus.GENUS_PRIMO.Service;

import com.genus.GENUS_PRIMO.Entity.Campus;
import com.genus.GENUS_PRIMO.Entity.Ecole;
import com.genus.GENUS_PRIMO.Exception.RessourceExistException;
import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Mapper.CampusMapper;
import com.genus.GENUS_PRIMO.Repository.CampusRepository;
import com.genus.GENUS_PRIMO.Repository.EcoleRepository;
import com.genus.GENUS_PRIMO.Service.Interface.ICampusService;
import com.genus.GENUS_PRIMO.dto.campus_dto.CampusRequest;
import com.genus.GENUS_PRIMO.dto.campus_dto.CampusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CampusServiceImpl implements ICampusService {
    private final CampusRepository campusRepository;
    private final CampusMapper campusMapper;
    private final EcoleRepository ecoleRepo;


    @Override
    public CampusResponse addCampus(CampusRequest campusRequest) {
        Campus campus = this.campusMapper.fromCampusRequest(campusRequest);
        Optional<Campus> campusAsked = this.campusRepository.getCampus(campusRequest.getEcoleId(), campusRequest.getName());
        if (campusAsked.isPresent()){
            throw new RessourceExistException("Campus already exist !!!");
        }
        Ecole ecole = this.ecoleRepo.findById(campusRequest.getEcoleId())
                .orElseThrow(()->new RessourceNotFoundException("Not found, try again"));
        campus.setEcole(ecole);
        campus.setCreatedAt(new Date());
        return this.campusMapper.fromCampus(this.campusRepository.save(campus));
    }

    @Override
    public List<CampusResponse> getCampus(Long id) {
        List<Campus> campusList = this.campusRepository.searchBySchool(id);
        List<CampusResponse> campusResponses = new ArrayList<>();
        campusList.forEach(cycle -> campusResponses.add(this.campusMapper.fromCampus(cycle)));
        return campusResponses;
    }

    @Override
    public CampusResponse editCampus(Long id, CampusRequest campusRequest) {
        try{
            Campus campus = this.campusMapper.fromCampusRequest(campusRequest);
            Optional<Campus> campusAsked = this.campusRepository.getCampus(campusRequest.getEcoleId(), campusRequest.getName());
            if (campusAsked.isPresent()){
                throw new RessourceExistException("Campus already exist !!!");
            }
            Campus campusUpdated = this.campusRepository.findById(id).get();
            campusUpdated.setName(campus.getName());
            campusUpdated.setAddress(campus.getAddress());
            campusUpdated.setResponsible(campus.getResponsible());
            campusUpdated.setUpdatedAt(new Date());
            return this.campusMapper.fromCampus(this.campusRepository.saveAndFlush(campusUpdated));
        }catch (NoSuchElementException e){
            throw new RessourceNotFoundException("Not found");
        }
    }

    @Override
    public CampusResponse deleteCampus(Long id) {
        try{
            Campus campusUpdated = this.campusRepository.findById(id).get();
            campusUpdated.setDeleted(true);
            return this.campusMapper.fromCampus(this.campusRepository.saveAndFlush(campusUpdated));
        }catch (NoSuchElementException e){
            throw new RessourceNotFoundException("Impossible to delete this campus");
        }
    }
}
