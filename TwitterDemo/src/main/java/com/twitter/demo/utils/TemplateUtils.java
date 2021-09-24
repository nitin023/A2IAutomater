package com.twitter.demo.utils;

import com.twitter.demo.Constant.EmailTemplate;
import com.twitter.demo.DTO.CommunicationData;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

public class TemplateUtils {

    public static String getEmailTemplate(String template) {
        StringBuilder htmlTemplate = new StringBuilder("");
        try {
            FileReader fileReader = new FileReader("src/main/resources/templates/" + getTemplateType(template));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Stream<String> lines = bufferedReader.lines();

            lines.forEach(htmlTemplate::append);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return htmlTemplate.toString();
    }

    public static String readFile(String template)
    {
        String fileName = "";
        if(template.equals(EmailTemplate.BOOKING_INFORMATION))
            fileName = EmailTemplate.BOOKING_FILE;
        StringBuilder string = new StringBuilder("");
        try {
        FileReader fileReader = new FileReader("src/main/resources/templates/" + fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String curLine;
        while ((curLine = bufferedReader.readLine()) != null){
                string.append(curLine);
                string.append(System.lineSeparator());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return string.toString();
    }

    public static String getTemplateType(String template) {
        String response = "";
        if (template != null && !template.isEmpty())
            switch (template) {
                case EmailTemplate.APP_VERIFY:
                    response = "AccountVerification.html";
                    break;
                case EmailTemplate.BOOKING_INFORMATION:
                    response = "UserBookingInformationTemplate.html";
                    break;
                case EmailTemplate.USER_INTERACTIVE_TEMPLATE:
                    response = "UserInteractiveTemplate.html";
                    break;
            }
        return response;

    }

    public static String replaceContnetInTemplate(CommunicationData communicationData)
    {
        String templateName = communicationData.getTemplateName();
        String template = communicationData.getContent();
        String content = "";
        if (templateName != null && template!=null && !templateName.isEmpty()) {
            switch (templateName) {
                case EmailTemplate.APP_VERIFY:
                    content = communicationData.getContent();
                    break;
                case EmailTemplate.BOOKING_FILE:
                case EmailTemplate.BOOKING_INFORMATION:
                    content = StringUtils.replaceEach(template, new String[]{"userName","bookingId","bookingDate","bookingVenue"}, new String[]{communicationData.getName(),
                    communicationData.getAppointmentDTO().getBookingId(),communicationData.getAppointmentDTO().getDate(),
                            communicationData.getAppointmentDTO().getInspectionPointCity()+communicationData.getAppointmentDTO().getInspectionPointName()});
                    break;
                case EmailTemplate.USER_INTERACTIVE_TEMPLATE:
                    content = StringUtils.replaceEach(template, new String[]{"bookingId","bookingDate"}, new String[]{communicationData.getAppointmentDTO().getBookingId(),
                    communicationData.getAppointmentDTO().getDate()});
                    break;
            }
        }
        return content;
    }
}
