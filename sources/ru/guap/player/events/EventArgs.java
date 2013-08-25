package ru.guap.player.events;

import java.util.*;

public class EventArgs {
	static public final EventArgs Empty = null;

	public static class Arg {
		String n;
		Object v;
		
		public Arg ( String name, Object value ) {
			n = name;
			v = value;
		}
		
		@Override
		public String toString () {
			return "(" + n + ": " + v + ")";
		}
	}

	private Map < String, Object > myArgs;
	
	public EventArgs ( Arg ... args ) {
		myArgs = new HashMap < String, Object > ();
		for ( Arg a: args ) {
			myArgs.put ( a.n, a.v );
		}
	}
	
	public Object get ( String name ) {
		return myArgs.get ( name );
	}
	
	@Override
	public String toString () {
		return myArgs.toString ();
	}
}