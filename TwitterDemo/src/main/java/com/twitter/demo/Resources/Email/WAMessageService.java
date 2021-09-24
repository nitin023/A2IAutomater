package com.twitter.demo.Resources.Email;

import com.twitter.demo.Constant.ApplicationConstants;
import com.twitter.demo.DTO.BookedLeadsDto;
import com.twitter.demo.Services.IWAMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

@Service
public class WAMessageService implements IWAMessageService {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendWAMessage (BookedLeadsDto bookedLeadsDto) {
       /* StringBuffer message = new StringBuffer("Dear user, Thank you for choosing OLX Autos to get your carName Inspected on 200 Parameters");
        message.append("Choose your options:1 njnj, 2 dnjnj" );
       // StringUtil

       try {
           StringBuffer data = new StringBuffer();
           data.append("method=" + ApplicationConstants.WA_METHOD);
           data.append("&userid=" + ApplicationConstants.WA_USERID);
           data.append("&password=" + URLEncoder.encode(ApplicationConstants.WA_PASSWORD, "UTF-8"));
           data.append("&msg=" + URLEncoder.encode(message.toString(), "UTF-8"));
           data.append("&send_to=" + URLEncoder.encode(bookedLeadsDto.getContactNumber(), "UTF-8"));
           data.append("&v=" + ApplicationConstants.WA_API_VERSION);
           data.append("&msg_type=" + ApplicationConstants.WA_MESSAGE_TYPE);
           data.append("&auth_scheme=" + ApplicationConstants.WA_AUTH_SCHEME);
           data.append("&format=json");

           //data += "method=sendMessage";
            //data += "&userid=20000xxxxx"; // your loginId
            //data += "&password=" + URLEncoder.encode("xxxxxx", "UTF-8"); // your password
            //data += "&msg=" + URLEncoder.encode("AIR2WEB message" + mydate.toString(), "UTF-8");
           //data += "&send_to=" + URLEncoder.encode("9xxxxxxxxx", "UTF-8"); // a valid 10 digit phone no.
            //data += "&v=1.1" ;
           // data += "&msg_type=TEXT"; // Can by "FLASH" or"UNICODE_TEXT" or “BINARY”
            //data += "&auth_scheme=PLAIN";

           *//* URL url = new URL(ApplicationConstants.WA_URL + data);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = br.readLine()) != null){
                buffer.append(line).append("\n");
            }
            System.out.println(buffer.toString());
            br.close();
            conn.disconnect();*//*

           String html = restTemplate.getForObject(ApplicationConstants.WA_URL + data.toString(), String.class);
           System.out.println(ApplicationConstants.WA_URL + data.toString());
           System.out.println(html);
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
    }
}
