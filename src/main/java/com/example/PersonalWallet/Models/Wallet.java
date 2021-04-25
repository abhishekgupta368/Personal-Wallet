package com.example.PersonalWallet.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer amount;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id",columnDefinition = "BIGINT UNSIGNED", nullable = false)
    List<UserTransaction> history = new ArrayList<>();;


    public Wallet(int amount){
        this.amount = amount;
    }

    public void storeHistory(UserTransaction transaction){
//        transaction.setWallet(this);
        history.add(transaction);
    }
}
