package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.*;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.BulletinMapper;
import com.genus.GENUS_SUP.Repository.AnneeAcademiqueRepository;
import com.genus.GENUS_SUP.Repository.BulletinRepository;
import com.genus.GENUS_SUP.Repository.EnseignantRepository;
import com.genus.GENUS_SUP.Service.Interface.IBulletinService;
import com.genus.GENUS_SUP.dto.bulletin_dto.BulletinRequest;
import com.genus.GENUS_SUP.dto.bulletin_dto.BulletinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BulletinServiceImpl implements IBulletinService {
    private final BulletinRepository bulletinRepo;
    private final BulletinMapper bulletinMapper;
    private final AnneeAcademiqueRepository anneeAcademiqueRepo;
    private final EnseignantRepository enseignantRepo;
    @Override
    public BulletinResponse addBulletin(BulletinRequest bulletinRequest) {
        Bulletin bulletin = this.bulletinMapper.fromBulletinRequest(bulletinRequest);
        Enseignant enseignant = this.enseignantRepo.findById(bulletinRequest.getEnseignantId())
                .orElseThrow(()-> new RessourceNotFoundException("Teacher with this id doesn't exist, try again !"));
        bulletin.setEnseignant(enseignant);
        AnneeAcademique annee = this.anneeAcademiqueRepo.findById(bulletinRequest.getAnneeAcademiqueId())
                .orElseThrow(()-> new RessourceNotFoundException("This annee academique doesn't exist, try again !"));
        bulletin.setAnneeAcademique(annee);
        bulletin.setDate(new Date());
        return this.bulletinMapper.fromBulletin(this.bulletinRepo.save(bulletin));
    }

    @Override
    public List<BulletinResponse> getAllBulletin(Long ecoleId) {
        List<Bulletin> bulletins = (List<Bulletin>) this.bulletinRepo.findBulletinByEcole(ecoleId);
        List<BulletinResponse> bulletinResponses = new ArrayList<>();
        bulletins.forEach(bulletin -> bulletinResponses.add(this.bulletinMapper.fromBulletin(bulletin)));
        return bulletinResponses;
    }

    @Override
    public List<BulletinResponse> getBulletin(Long enseignantId) throws RessourceNotFoundException {
        List<Bulletin> bulletins = (List<Bulletin>) this.bulletinRepo.findBulletinByEnseignant(enseignantId);
        List<BulletinResponse> bulletinResponses = new ArrayList<>();
        bulletins.forEach(bulletin -> bulletinResponses.add(this.bulletinMapper.fromBulletin(bulletin)));
        return bulletinResponses;
    }

    @Override
    public BulletinResponse getById(Long id) {
        return this.bulletinMapper.fromBulletin(this.bulletinRepo.findById(id).get());    }
}
