package com.onb.eventHowler;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class EventHowlerReceiverService extends Service{

	final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	private EventHowlerBroadcastReceiver eventHowlerBraoaBroadcastReceiver = new EventHowlerBroadcastReceiver();
	private IntentFilter SMS_RECEIVED_FILTER = new IntentFilter(SMS_RECEIVED);

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Toast.makeText(this, "creating event Howler Receiver service",
				Toast.LENGTH_SHORT).show();
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "event Howler Receiver service started",
				Toast.LENGTH_SHORT).show();
		registerReceiver(eventHowlerBraoaBroadcastReceiver, SMS_RECEIVED_FILTER);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "event Howler receiving service destroyed",
				Toast.LENGTH_SHORT).show();
		unregisterReceiver(eventHowlerBraoaBroadcastReceiver);
		super.onDestroy();
	}
}
