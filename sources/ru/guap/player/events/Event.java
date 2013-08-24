package ru.guap.player.events;

import java.util.*;

public class Event implements IEvent {
	public static enum Type { BTN_CLICK };

	private List <IEvent> myEvents;

	public Event () {
		myEvents = new LinkedList <IEvent> ();
	}
	
	public void bind ( IEvent e ) {
		myEvents.add ( e );
	}
	
	public void unbind ( IEvent e ) {
		while ( myEvents.remove ( e ) );
	}
	
	public void unbindAll () {
		myEvents.clear();
	}
	
	public void run ( Object sender, EventArgs arg ) {
		for ( IEvent ev: myEvents ) {
			ev.run ( sender, arg );
		}
	}
}