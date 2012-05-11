package com.onb.eventHowler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

public class EventHowlerActivity extends Activity {
	
	private boolean hasEventOnGoing;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onSwitchToggled(View view){
    	ToggleButton toggleButton = (ToggleButton)view.findViewById(R.id.howl_toggle_button);
    	if(toggleButton.isChecked()){
    		hasEventOnGoing = true;
    		startService(new Intent(this, EventHowlerSenderService.class));
    		startService(new Intent(this, EventHowlerReceiverService.class));
    		Log.d("main activity.has event on going started", hasEventOnGoing + "");
    	}
    	else{
    		hasEventOnGoing = false;
    		stopService(new Intent(this, EventHowlerSenderService.class));
    		stopService(new Intent(this, EventHowlerReceiverService.class));
    		Log.d("main activity.has event on going stopped", hasEventOnGoing + "");
    	}
    }
}