package com.example.PersonalWallet.Services;

import com.example.PersonalWallet.Compoments.PaymentDetails;
import com.example.PersonalWallet.Compoments.UserRequest;
import com.example.PersonalWallet.Execptions.NotSufficientAmountException;
import com.example.PersonalWallet.Models.Person;
import com.example.PersonalWallet.Models.UserTransaction;
import com.example.PersonalWallet.Models.TransactionStatus;
import com.example.PersonalWallet.Models.Wallet;
import com.example.PersonalWallet.Repository.PersonRepository;
import com.example.PersonalWallet.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class MoneyService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    WalletRepository walletRepository;

    @Transactional
    public Person createUser(UserRequest userRequest){
        Wallet wallet = new Wallet(userRequest.getInitialAmount());
        Person person = new Person();
        person.setName(userRequest.getName());
        person.setContact(userRequest.getContact());
        person.setEmail(userRequest.getEmail());
        person.setPassword(userRequest.getPassword());
        person.setWallet(wallet);
        UserTransaction transaction = new UserTransaction(userRequest.getInitialAmount(),Instant.now(),TransactionStatus.DEPOSIT);
        person.getWallet().storeHistory(transaction);
        personRepository.save(person);
//        walletRepository.save(wallet);
        return person;
    }

    @Transactional
    public Person depositMoney(String contact,Integer money){
        Person person = personRepository.findByContact(contact);
        person.getWallet().setAmount(person.getWallet().getAmount()+money);
        UserTransaction transaction = new UserTransaction(money, Instant.now(), TransactionStatus.DEPOSIT);
        person.getWallet().storeHistory(transaction);
        personRepository.save(person);
        return person;
    }

    @Transactional
    public Person debitMoney(String contact,Integer money){
        Person person = personRepository.findByContact(contact);;

        if(person.getWallet().getAmount()-money<=0){
            throw new NotSufficientAmountException("Not sufficient balance");
        }
        else{
            person.getWallet().setAmount(person.getWallet().getAmount()-money);
            UserTransaction transaction = new UserTransaction(money, Instant.now(),TransactionStatus.DEBIT);
            person.getWallet().storeHistory(transaction);
            personRepository.save(person);
        }
        return person;
    }

    @Transactional
    public PaymentDetails transferFunds(String from, String to, Integer money){
        Person personFrom  = personRepository.findByContact(from);
        Person personTo = personRepository.findByContact(to);
        if(personFrom.getWallet().getAmount()-money<=0){
            throw new NotSufficientAmountException("Not sufficient balance");
        }
        else {
            personFrom.getWallet().setAmount(personFrom.getWallet().getAmount()-money);
            personTo.getWallet().setAmount(personTo.getWallet().getAmount()+money);

            UserTransaction transaction = new UserTransaction(money,Instant.now(),
                                    personFrom.getName()+personFrom.getContact(),
                                    personTo.getName()+personTo.getName(),TransactionStatus.TRANSFER);
            personFrom.getWallet().storeHistory(transaction);
            personTo.getWallet().storeHistory(transaction);
            personRepository.save(personFrom);
            personRepository.save(personTo);
        }
        return new PaymentDetails(personFrom,personTo);
    }

    public List<UserTransaction> getTransactions(String contact){
        Person person = personRepository.findByContact(contact);
        return person.getWallet().getHistory();
    }

    public void sendEmail(Person person){

    }

    public void sendEmail(Person from,Person to){

    }
}
