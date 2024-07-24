package com.genus.GENUS_PRIMO.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcoleModel {
    private Long id,po_box;
    private String name,image_url,address,city,country,responsible,phone,fax,email;
}
