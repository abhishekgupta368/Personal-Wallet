package com.example.PersonalWallet.Compoments;

import com.example.PersonalWallet.Models.Person;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {
    private Person from;
    private Person to;
}
