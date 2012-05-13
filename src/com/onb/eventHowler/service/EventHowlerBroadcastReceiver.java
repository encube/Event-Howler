package com.onb.eventHowler.service;

import com.onb.eventHowler.application.EventHowlerOpenDbHelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class EventHowlerBroadcastReceiver extends BroadcastReceiver{
	
	final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//test
		EventHowlerOpenDbHelper openHelper = new EventHowlerOpenDbHelper(context);
		Log.d("broadcastReceiver", openHelper.getCount() + "");
		//test
		
		if(intent.getAction().equals(SMS_RECEIVED)){

	        Bundle bundle = intent.getExtras();        
	        SmsMessage[] msgs = null;
	        String str = "";            
	        if (bundle != null)
	        {

	            Object[] pdus = (Object[]) bundle.get("pdus");
	            msgs = new SmsMessage[pdus.length];            
	            for (int i=0; i<msgs.length; i++){
	                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
	                str += "SMS from " + msgs[i].getOriginatingAddress();                     
	                str += " :";
	                str += msgs[i].getMessageBody().toString();
	                str += "\n";        
	            }

	            Toast.makeText(context, str, Toast.LENGTH_SHORT).show(); //for test
	        }}}

}
