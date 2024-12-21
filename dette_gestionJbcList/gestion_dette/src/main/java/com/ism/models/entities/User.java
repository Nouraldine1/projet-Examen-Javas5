package com.ism.models.entities;
import com.ism.models.enums.EtatCompte;
import com.ism.models.enums.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int id;
    private String email;
    private String password;
    private String identifiant;
    private Role role;
    private EtatCompte etat;
    private Client client;
}
