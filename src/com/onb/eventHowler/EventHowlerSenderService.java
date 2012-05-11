package com.onb.eventHowler;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

public class EventHowlerSenderService extends Service{
	 
	EventHowlerApplication application;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Toast.makeText(this, "creating event Howler Sender service",
				Toast.LENGTH_SHORT).show();
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "event Howler Sender service started",
				Toast.LENGTH_SHORT).show();
		application = (EventHowlerApplication)getApplication();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < application.getPortCount(); i++){
					sendSMS(application.getPortAtIndex(i).getPhoneNumber(), "hi, please come " + application.getPortAtIndex(i).getName());
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						
					}
				}
			}
		}).start();
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
