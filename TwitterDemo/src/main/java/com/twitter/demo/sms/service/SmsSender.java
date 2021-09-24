package com.twitter.demo.sms.service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {

    public static final String ACCOUNT_SID = "AC38c43e7eced8d9099d8a9677156066d8";
    public static final String AUTH_TOKEN = "770d11f59c8398a8b40326c86f8c88e1";
    public static final String from_number = "+13467014948";

    public void sendSMS(String toPhoneNumber)
    {
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(toPhoneNumber),
                        new com.twilio.type.PhoneNumber(from_number),
                        "Hi from twilio").create();
        System.out.println("Hi from twilio");

    }


}
