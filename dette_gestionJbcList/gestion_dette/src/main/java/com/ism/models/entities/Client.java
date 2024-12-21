package com.ism.models.entities;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  
public class Client {
    private String id;
    private String nom;
    private String telephone;
    private String adresses;
    private double montantCountTotalDu;

}
