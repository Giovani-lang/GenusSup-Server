package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.AnneeAcademique;
import com.logonedigital.PI.SCHULE.Entity.Etudiant;
import com.logonedigital.PI.SCHULE.Entity.Paiement;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.PaiementMapper;
import com.logonedigital.PI.SCHULE.Repository.AnneeAcademiqueRepository;
import com.logonedigital.PI.SCHULE.Repository.EtudiantRepository;
import com.logonedigital.PI.SCHULE.Repository.PaiementRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.IPaiementService;
import com.logonedigital.PI.SCHULE.dto.paiement_dto.PaiementRequest;
import com.logonedigital.PI.SCHULE.dto.paiement_dto.PaiementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {
    private final PaiementRepository paiementRepo;
    private final PaiementMapper paiementMapper;
    private final EtudiantRepository etudiantRepo;
    private final AnneeAcademiqueRepository anneeAcademiqueRepo;
    @Override
    public PaiementResponse addPaiement(PaiementRequest paiementRequest) {
        Paiement paiement = this.paiementMapper.fromPaiementRequest(paiementRequest);
        Etudiant etd = this.etudiantRepo.findById(paiementRequest.getEtudiantId())
                .orElseThrow(()-> new RessourceNotFoundException("Student with this id doesn't exist, try again !"));
        paiement.setEtudiant(etd);
        AnneeAcademique annee = this.anneeAcademiqueRepo.findById(paiementRequest.getAnneeAcademiqueId())
                .orElseThrow(()-> new RessourceNotFoundException("This annee academique doesn't exist, try again !"));
        paiement.setAnneeAcademique(annee);
        paiement.setDate(new Date());
        return this.paiementMapper.fromPaiement(this.paiementRepo.save(paiement));
    }

    @Override
    public List<PaiementResponse> getPaiement(Long etudiantId, Long anneeId) throws RessourceNotFoundException{
        List<Paiement> paiements = (List<Paiement>) this.paiementRepo.findPaiementByMatricule(etudiantId,anneeId);
        List<PaiementResponse> paiementResponses = new ArrayList<>();
        paiements.forEach(paiement -> paiementResponses.add(this.paiementMapper.fromPaiement(paiement)));
        return paiementResponses;
    }

    @Override
    public PaiementResponse getById(Long id) {
        return this.paiementMapper.fromPaiement(this.paiementRepo.findById(id).get());
    }

}
