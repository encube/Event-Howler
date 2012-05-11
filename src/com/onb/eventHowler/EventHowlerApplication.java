package com.onb.eventHowler;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

public class EventHowlerApplication extends Application{
	
	private List<EventHowlerParticipant> participants = new ArrayList<EventHowlerParticipant>();
	private boolean hasOnHoingEvent;

	@Override
	public void onCreate() {
		participants.add(new EventHowlerParticipant("novo", "5556", "SENDING"));
		participants.add(new EventHowlerParticipant("naga", "5558", "SENDING"));
		super.onCreate();
	}
	
	public EventHowlerParticipant getPortAtIndex(int index){
		return participants.get(index);
	}
	
	public int getPortCount(){
		return participants.size();
	}
	
	public boolean hasOnGoingEvent(){
		return hasOnHoingEvent;
	}
}
