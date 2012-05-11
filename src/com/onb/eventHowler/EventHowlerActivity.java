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
    	ToggleButton toggleButton = (ToggleButton)view.findViewById(R.id.howl_toggle_button);
    	if(toggleButton.isChecked()){
    		startService(new Intent(this, EventHowlerSenderService.class));
    		startService(new Intent(this, EventHowlerReceiverService.class));
    	}
    	else{
    		stopService(new Intent(this, EventHowlerSenderService.class));
    		stopService(new Intent(this, EventHowlerReceiverService.class));
    	}
    }
}