package edu.fundup.model.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import edu.fundup.model.iservice.IServiceSms;

import java.util.Random;

public class TwilioService implements IServiceSms {
    public static final String ACCOUNT_SID = "AC45ca2c01d6346c642cdec7ef811fddd7";
    public static final String AUTH_TOKEN = "b04f28a2ab2942adc1df83abd83353fb";
    String phoneNumber;
    String userName;
    
    public TwilioService(){
///*
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message.creator(new PhoneNumber(phoneNumber),
//                new PhoneNumber("+13344215880"),
//                "Hello"+  userName +" your Verification code is "+ generateCode().toString()).create();
//
//        System.out.println(message.getSid());*/
//    	System.out.println("MSG SENT TO " + phoneNumber+ " " + userName);
    	//sendSms(phoneNumber, userName);
    }
    
    

    public Integer generateCode(){
        Random rand = new Random();
        Integer code = rand.nextInt(9000
                - 1000 + 1) + 8;
          return code;
     }



	@Override
	public void sendSms(String phoneNumber, String userName) {
		// TODO Auto-generated method stub
		/*
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(phoneNumber),
                new PhoneNumber("+13344215880"),
                "Hello"+  userName +" your Verification code is "+ generateCode().toString()).create();

        System.out.println(message.getSid());*/
    	System.out.println("MSG SENT TO " + phoneNumber+ " " + userName);
		
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}



