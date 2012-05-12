package com.onb.eventHowler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class EventHowlerActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onSwitchToggled(View view){
    	EventHowlerApplication application = (EventHowlerApplication)getApplication();
    	ToggleButton toggleButton = (ToggleButton)view.findViewById(R.id.howl_toggle_button);
    	if(toggleButton.isChecked()){
    		application.setHasOnGoingEvent(true);
    		startService(new Intent(this, EventHowlerSenderService.class));
    		startService(new Intent(this, EventHowlerReceiverService.class));
    	}
    	else{
    		application.setHasOnGoingEvent(false);
    		stopService(new Intent(this, EventHowlerReceiverService.class));
    	}
    }
}