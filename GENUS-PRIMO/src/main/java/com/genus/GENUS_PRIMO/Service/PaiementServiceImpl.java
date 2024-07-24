package com.genus.GENUS_PRIMO.Service;

import com.genus.GENUS_PRIMO.Entity.AnneeAcademique;
import com.genus.GENUS_PRIMO.Entity.Etudiant;
import com.genus.GENUS_PRIMO.Entity.Paiement;
import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Mapper.PaiementMapper;
import com.genus.GENUS_PRIMO.Repository.AnneeAcademiqueRepository;
import com.genus.GENUS_PRIMO.Repository.EtudiantRepository;
import com.genus.GENUS_PRIMO.Repository.PaiementRepository;
import com.genus.GENUS_PRIMO.Service.Interface.IPaiementService;
import com.genus.GENUS_PRIMO.dto.paiement_dto.PaiementRequest;
import com.genus.GENUS_PRIMO.dto.paiement_dto.PaiementResponse;
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
    private final EmailService emailService;

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

        Paiement payment = this.paiementRepo.save(paiement);
        this.emailService.sendUserNotificationEmail(payment.getEtudiant());
        return this.paiementMapper.fromPaiement(payment);
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
