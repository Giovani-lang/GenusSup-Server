package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Campus;
import com.genus.GENUS_SUP.Entity.Departement;
import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.DepartementMapper;
import com.genus.GENUS_SUP.Repository.CampusRepository;
import com.genus.GENUS_SUP.Repository.DepartementRepository;
import com.genus.GENUS_SUP.Service.Interface.IDepartementService;
import com.genus.GENUS_SUP.dto.departement_dto.DepartementRequest;
import com.genus.GENUS_SUP.dto.departement_dto.DepartementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartementServiceImpl implements IDepartementService {
    private final DepartementRepository departementRepo;
    private final DepartementMapper departementMapper;
    private final CampusRepository campusRepo;

    @Override
    public DepartementResponse addDepartement(DepartementRequest departementRequest) {
        Departement departement = this.departementMapper.fromDepartemnetRequest(departementRequest);
        Optional<Departement> departementAsked = this.departementRepo.getDepartement(departementRequest.getCampusId(), departementRequest.getNom());
        if (departementAsked.isPresent()){
            throw new RessourceExistException("Department already exist !!!");
        }
        Campus campus = this.campusRepo.findById(departementRequest.getCampusId())
                .orElseThrow(()->new RessourceNotFoundException("Not found, try again"));
        departement.setCampus(campus);
        return this.departementMapper.fromDepartement(this.departementRepo.save(departement));
    }

    @Override
    public List<DepartementResponse> getDepartement(Long id) {
        List<Departement> departements = this.departementRepo.findAllByEcole(id);
        List<DepartementResponse> departementResponses = new ArrayList<>();
        departements.forEach(departement -> departementResponses.add(this.departementMapper.fromDepartement(departement)));
        return departementResponses;
    }

    @Override
    public DepartementResponse editDepartement(Long id, DepartementRequest departementRequest) {
        try{
            Departement departement = this.departementMapper.fromDepartemnetRequest(departementRequest);
            Departement departemnetUpdated = this.departementRepo.findById(id).get();
            Optional<Departement> departementAsked = this.departementRepo.getDepartement(departementRequest.getCampusId(), departementRequest.getNom());
            if (departementAsked.isPresent()){
                throw new RessourceExistException("Cycle already exist !!!");
            }
            Campus campus = this.campusRepo.findById(departementRequest.getCampusId())
                    .orElseThrow(()->new RessourceNotFoundException("Not found, try again"));
            departemnetUpdated.setCampus(campus);
            departemnetUpdated.setNom(departement.getNom());
            departemnetUpdated.setChief(departement.getChief());
            return this.departementMapper.fromDepartement(this.departementRepo.saveAndFlush(departemnetUpdated));
        }catch (NoSuchElementException e){
            throw new RessourceNotFoundException("Not found");
        }
    }
    @Override
    public DepartementResponse deleteDepartement(Long id) {
        try{
            Departement departement = this.departementRepo.findById(id).get();
            departement.setDeleted(true);
            return this.departementMapper.fromDepartement(this.departementRepo.saveAndFlush(departement));
        }catch (NoSuchElementException e){
            throw new RessourceNotFoundException("Impossible to delete this cycle");
        }
    }

}
