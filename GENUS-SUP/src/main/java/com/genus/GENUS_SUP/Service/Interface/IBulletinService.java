package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.bulletin_dto.BulletinRequest;
import com.genus.GENUS_SUP.dto.bulletin_dto.BulletinResponse;

import java.util.List;

public interface IBulletinService {
    BulletinResponse addBulletin (BulletinRequest bulletinRequest);
    List<BulletinResponse> getAllBulletin(Long ecoleId);
    List<BulletinResponse> getBulletin(Long enseignantId);

    BulletinResponse getById(Long id);
}
