package com.genus.GENUS_SUP.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcoleModel {
    private Long id,po_box;
    private String name,image_url,site,address,city,country,responsible,phone,fax,email;
}
