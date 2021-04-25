package com.example.PersonalWallet.Repository;

import com.example.PersonalWallet.Models.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<UserTransaction,Long> {
}
