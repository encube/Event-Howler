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
				int i = 0;
				while(true){
					if(application.getPaticipantAtIndex(i).getStatus() == "FOR_SEND"){
						sendSMS(application.getPaticipantAtIndex(i).getPhoneNumber(),
								application.getInvitationMessage() + application.getPaticipantAtIndex(i).getName());
						application.getPaticipantAtIndex(i).setStatus("SENT");
					}
					
					try {
						Thread.sleep(2000);
					}
					catch (Exception e) {}
					
					if(i+1<application.getParticipantCount()){
						i++;
					}
					else if(application.hasOnGoingEvent()){
						i=0;
					}
					else{
						break;
					}
				}
				stopSelf();
			}
		}).start();
		return Service.START_NOT_STICKY;
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
