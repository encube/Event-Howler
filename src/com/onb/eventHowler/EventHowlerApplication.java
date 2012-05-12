package com.onb.eventHowler;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

public class EventHowlerApplication extends Application{
	
	private List<EventHowlerParticipant> participants = new ArrayList<EventHowlerParticipant>();
	private boolean hasOnHoingEvent;
	private String replyMessage;
	private String invitationMessage;

	@Override
	public void onCreate() {
		participants.add(new EventHowlerParticipant("novo", "5556", "FOR_SEND"));
		participants.add(new EventHowlerParticipant("naga", "5558", "FOR_SEND"));
		replyMessage = "thank you";
		invitationMessage = "hello, please come to an event here. ";
		super.onCreate();
	}
	
	public EventHowlerParticipant getPaticipantAtIndex(int index){
		return participants.get(index);
	}
	
	public int getParticipantCount(){
		return participants.size();
	}
	
	public boolean hasOnGoingEvent(){
		return hasOnHoingEvent;
	}
	
	public void setHasOnGoingEvent(boolean b){
		hasOnHoingEvent = b;
	}
	
	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}

	public String getInvitationMessage() {
		return invitationMessage;
	}

	public void setInvitationMessage(String invitationMessage) {
		this.invitationMessage = invitationMessage;
	}
	
	public void updateStatus(String phoneNumber, String status){
		for(int i = 0; i < participants.size(); i++){
			if(getPaticipantAtIndex(i).getPhoneNumber() == phoneNumber){
				getPaticipantAtIndex(i).setStatus(status);
			}
		}
	}
}
