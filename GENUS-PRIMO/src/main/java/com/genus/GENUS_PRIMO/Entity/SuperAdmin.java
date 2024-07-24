package com.genus.GENUS_PRIMO.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_super_admin")
public class SuperAdmin extends User {
    private Date createdAt, updatedAt;
}
