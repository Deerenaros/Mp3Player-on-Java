package ru.guap.player.events;

import java.util.*;

public class EventArgs {
	static public final EventArgs Empty = new EventArgs ();

	public class Arg {
		String n;
		Object v;
		
		public Arg ( String name, Object value ) {
			name = n;
			value = v;
		}
	}

	private Map < String, Object > myArgs;
	
	public EventArgs ( Arg ... args ) {
		myArgs = new HashMap < String, Object > ();
		for ( Arg a: args ) {
			myArgs.put ( a.n, a.v );
		}
	}
	
	public Object getArg ( String name ) {
		return myArgs.get ( name );
	}
}