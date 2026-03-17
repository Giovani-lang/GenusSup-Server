package com.genus.GENUS_SUP.dto.campus_dto;

import com.genus.GENUS_SUP.Model.CycleModel;
import com.genus.GENUS_SUP.Model.DepartementModel;
import com.genus.GENUS_SUP.dto.admin_dto.AdminResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CampusResponse {
    private Long  id;
    private String name,address;
    private AdminResponseDTO responsible;
    private List<DepartementModel> departements;
}
