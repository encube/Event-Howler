package com.onb.eventHowler.service;

import com.onb.eventHowler.application.*;
import com.onb.eventHowler.domain.EventHowlerParticipant;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class EventHowlerSenderService extends Service{
	 
	private static EventHowlerApplication application;
	private static EventHowlerOpenDbHelper openHelper;
	
	private static final int COLUMN_PNUMBER  = 0, 
			COLUMN_NAME  = 1, 
			COLUMN_STATUS  = 2,
			COLUMN_MESSAGES = 1,
			INITIAL_POSITION = 0;

	
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
				
		openHelper = new EventHowlerOpenDbHelper(getApplicationContext());
		
		EventHowlerParticipant participant1 = new EventHowlerParticipant("novo", "5554", "FOR_SEND");
		EventHowlerParticipant participant2 = new EventHowlerParticipant("naga", "5556", "FOR_SEND");
		openHelper.insertParticipant(participant1);
		openHelper.insertParticipant(participant2);
		openHelper.populateMessages("Hello fella, i would like to invite for a pack party ", 
				"thank you, we receive your reply ", "yes", "no");
		
		Toast.makeText(this, "event Howler Sender service started",
				Toast.LENGTH_SHORT).show();
		application = (EventHowlerApplication)getApplication();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i = 0;
				Cursor participantCursor = openHelper.getAllParticipant();
				Cursor messageCursor = openHelper.getAllMesssages();
				messageCursor.moveToPosition(INITIAL_POSITION);
				String invitationMessage = messageCursor.getString(COLUMN_MESSAGES);
				
				while(true){
					participantCursor.moveToPosition(i);
					if(participantCursor.getString(COLUMN_STATUS).equals("FOR_SEND")){
						sendSMS(participantCursor.getString(COLUMN_PNUMBER),
								invitationMessage + participantCursor.getString(COLUMN_NAME));
						
						//TODO change to broadcast receiver to listen for sentIntent
						openHelper.updateStatus(new EventHowlerParticipant(participantCursor.getString(COLUMN_NAME),
								participantCursor.getString(COLUMN_PNUMBER),
								"SENT"));
						Log.d("sending sms", participantCursor.getString(0)+participantCursor.getString(1)+participantCursor.getString(2));
					}
					
					Log.d("in loop", "running");
					try {
						Thread.sleep(2000);
					}
					catch (Exception e) {Log.d("sender service", "nag-pupuyat, ayaw magsleep");}
					
					if(i+1<participantCursor.getCount()){
						i++;
					}
					else if(application.hasOnGoingEvent()){
						i=0;
						participantCursor = openHelper.getAllParticipant();
					}
					else{
						messageCursor.close();
						participantCursor.close();
						openHelper.resetDatabase();
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
