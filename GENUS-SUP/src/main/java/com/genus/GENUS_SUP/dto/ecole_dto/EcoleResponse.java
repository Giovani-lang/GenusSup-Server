package com.genus.GENUS_SUP.dto.ecole_dto;

import com.genus.GENUS_SUP.Model.CampusModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EcoleResponse {
    private Long id;
    private String image_url,name,acronym,email,site,address,fax,phone,responsible,responsible_phone,country,city;
    private Long po_box;
    private List<CampusModel> campus;
}
