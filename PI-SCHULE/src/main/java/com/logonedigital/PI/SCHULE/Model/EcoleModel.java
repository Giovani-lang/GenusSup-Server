package com.logonedigital.PI.SCHULE.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcoleModel {
    private Long id;
    private String name;
    private String image_url;
}
