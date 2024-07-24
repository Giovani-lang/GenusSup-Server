package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.parent_dto.ParentRequest;
import com.genus.GENUS_PRIMO.dto.parent_dto.ParentResponse;

import java.util.List;

public interface IParentService {
    ParentResponse addParent (ParentRequest parentRequest);
    ParentResponse editParent (String email, ParentRequest parentRequest);
    List<ParentResponse> search (Long ecoleId);
    ParentResponse getParent (String email);
}
