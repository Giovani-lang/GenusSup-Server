package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Administration;
import com.genus.GENUS_SUP.Entity.Campus;
import com.genus.GENUS_SUP.Entity.Ecole;
import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.CampusMapper;
import com.genus.GENUS_SUP.Repository.AdminRepo;
import com.genus.GENUS_SUP.Repository.CampusRepository;
import com.genus.GENUS_SUP.Repository.EcoleRepository;
import com.genus.GENUS_SUP.Service.Interface.ICampusService;
import com.genus.GENUS_SUP.dto.campus_dto.CampusRequest;
import com.genus.GENUS_SUP.dto.campus_dto.CampusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CampusServiceImpl implements ICampusService {
    private final CampusRepository campusRepository;
    private final CampusMapper campusMapper;
    private final EcoleRepository ecoleRepo;
    private final AdminRepo adminRepo;


    @Override
    public CampusResponse addCampus(CampusRequest campusRequest) {
        Campus campus = this.campusMapper.fromCampusRequest(campusRequest);
        Optional<Campus> campusAsked = this.campusRepository.getCampus(campusRequest.getEcoleId(), campusRequest.getName());
        if (campusAsked.isPresent()){
            throw new RessourceExistException("Campus already exist !!!");
        }
        Administration admin = this.adminRepo.findById(campusRequest.getResponsibleId())
                .orElseThrow(()-> new RessourceNotFoundException("Not found, try again"));
        campus.setResponsible(admin);
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
            Administration admin = this.adminRepo.findById(campusRequest.getResponsibleId())
                    .orElseThrow(()-> new RessourceNotFoundException("Not found, try again"));
            campusUpdated.setResponsible(admin);
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
