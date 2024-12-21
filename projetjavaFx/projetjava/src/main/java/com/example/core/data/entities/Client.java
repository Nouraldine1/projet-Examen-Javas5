package com.example.core.data.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String surname;
    private String telephone;
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private User userAccount;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<DemandeDette> demandeDettes;

    public Client(String surname, String telephone, String address, User userAccount,
            List<DemandeDette> demandeDettes) {
        this.surname = surname;
        this.telephone = telephone;
        this.address = address;
        this.userAccount = userAccount;
        this.demandeDettes = demandeDettes;
    }

    public Client(){
        
    }

    @Override
    public String toString() {
        return "Client{id=" + id + ", surname='" + surname + "', telephone='" + telephone + "', address='" + address
                + "'}";
    }

}
