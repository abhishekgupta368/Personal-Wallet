package com.example.PersonalWallet.Repository;

import com.example.PersonalWallet.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface PersonRepository extends JpaRepository<Person,Long> {
    Person findByContact(String contact);
}
