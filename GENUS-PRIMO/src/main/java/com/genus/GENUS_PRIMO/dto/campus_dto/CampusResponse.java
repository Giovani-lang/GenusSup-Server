package com.genus.GENUS_PRIMO.dto.campus_dto;

import com.genus.GENUS_PRIMO.Model.CycleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CampusResponse {
    private Long  id;
    private String name,address,responsible;
    private List<CycleModel> cycles;
}
