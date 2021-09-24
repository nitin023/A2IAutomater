package com.twitter.demo.sms.service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twitter.demo.Constant.EmailTemplate;
import com.twitter.demo.DTO.CommunicationData;
import com.twitter.demo.utils.TemplateUtils;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {

    public static final String ACCOUNT_SID = "AC38c43e7eced8d9099d8a9677156066d8";
    public static final String AUTH_TOKEN = "2fa915a45d664917c468c65a8f8867cb";
    public static final String from_number = "+13467014948";

    public void sendSMS(CommunicationData communicationData)
    {
        String str = TemplateUtils.readFile(communicationData.getTemplateName());
        communicationData.setContent(str);
        String content = TemplateUtils.replaceContnetInTemplate(communicationData);
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(communicationData.getToPhoneNumber()),
                        new com.twilio.type.PhoneNumber(from_number),
                        content).create();

    }


}
