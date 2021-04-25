package com.example.PersonalWallet.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer money;
    private Instant time;
    private String from;
    private String to;
    private TransactionStatus transactionStatus;

    public UserTransaction(Integer money, Instant time, TransactionStatus transactionStatus) {
        this.money = money;
        this.time = time;
        this.transactionStatus = transactionStatus;
    }

    public UserTransaction(Integer money, Instant time, String from, String to, TransactionStatus transactionStatus) {
        this.money = money;
        this.time = time;
        this.from = from;
        this.to = to;
        this.transactionStatus = transactionStatus;
    }
}
