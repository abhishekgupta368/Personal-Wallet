package com.example.PersonalWallet.Controllers;

import com.example.PersonalWallet.Compoments.PaymentDetails;
import com.example.PersonalWallet.Compoments.UserRequest;
import com.example.PersonalWallet.Models.Person;
import com.example.PersonalWallet.Models.UserTransaction;
import com.example.PersonalWallet.Services.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    MoneyService moneyService;

    @PostMapping("/createUser")
    ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        System.out.println("posted");
        Person person = moneyService.createUser(userRequest);
        return new ResponseEntity<>(person,HttpStatus.OK);
    }

    @PostMapping("/addMoney")
    ResponseEntity<?> addMoney(@RequestParam("contact") String contact,@RequestParam("money") Integer money){
        Person person = moneyService.depositMoney(contact,money);
        return new ResponseEntity<>(person, HttpStatus.ACCEPTED);
    }

    @PostMapping("/debitMoney")
    ResponseEntity<?> debitMoney(@RequestParam("contact") String contact,@RequestParam("money") Integer money){
        Person person = moneyService.debitMoney(contact,money);
        return new ResponseEntity<>(person, HttpStatus.ACCEPTED);
    }

    @PostMapping("/transferMoney")
    ResponseEntity<?> transferMoney(@RequestParam("from") String from,@RequestParam("to") String to,@RequestParam("money")Integer money){
        PaymentDetails paymentDetails = moneyService.transferFunds(from,to,money);
        return new ResponseEntity<>(paymentDetails,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getTransactionHistory")
    ResponseEntity<?> getTransactionHistory(@RequestParam("contact") String contact){
        List<UserTransaction> transaction = moneyService.getTransactions(contact);
        return new ResponseEntity<>(transaction,HttpStatus.ACCEPTED);
    }

}
