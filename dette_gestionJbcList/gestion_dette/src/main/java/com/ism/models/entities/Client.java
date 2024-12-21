package com.ism.models.entities;
import lombok.*;
import com.ism.models.entities.User;;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  
public class Client {
    private long id;
    private String nom;
    private String telephone;
    private String adresse;
    private double MontantTotalDu;
    private User user;


}
