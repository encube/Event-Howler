package com.onb.eventHowler;

import java.util.ArrayList;
import java.util.List;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

public class EventHowlerSenderService extends Service{
	
	List<String> portNumbers = new ArrayList<String>(); //for test
	String message = "hello, please come"; //for test

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Toast.makeText(this, "creating event Howler Sender service",
				Toast.LENGTH_SHORT).show();
		portNumbers.add("5558"); //for test
		portNumbers.add("5556"); //for test
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "event Howler Sender service started",
				Toast.LENGTH_SHORT).show();
		for(int i=0; i<portNumbers.size(); i++){ //for test
			sendSMS(portNumbers.get(i), message); //for test
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "event Howler sending service destroyed",
				Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}
	
	private void sendSMS(String phoneNumber, String message) {                
	    SmsManager sms = SmsManager.getDefault();
	    sms.sendTextMessage(phoneNumber, null, message, null, null);     
	}

}
