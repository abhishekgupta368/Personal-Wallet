package com.example.PersonalWallet.Execptions;

public class NotSufficientAmountException extends RuntimeException{
    public NotSufficientAmountException(String message){
        super(message);
    }
}
