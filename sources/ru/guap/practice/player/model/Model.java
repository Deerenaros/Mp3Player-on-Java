package ru.guap.practice.player.model;

import java.util.*;
import java.io.*;

import ru.guap.practice.player.generall.*;

public class Model {
	public static class Playlist {
		List < Track > _playlist;
		int current;
		
		Playlist () {
			_playlist = new LinkedList ();
			current = 0;
		}
		
		public void add ( File f ) throws Exception {
			_playlist.add ( new Track ( f ) );
		}
		
		public void add ( Track t ) {
			_playlist.add ( t );
		}
		
		public void clear () {
			_playlist.clear ();
		}
		
		public void next () {
			if ( _playlist.size () > 0 ) {
				current = ( current + 1 ) % _playlist.size ();
			}
		}
		
		public void prev () {
			if ( _playlist.size () > 0 ) {
				current = ( current - 1 + _playlist.size () ) % _playlist.size ();
			}
		}
		
		public Track[] getTracks () {
			return _playlist.toArray ( new Track[ 0 ] );
		}
		
		public Track getCurrent () throws Exception {
			if ( current >= _playlist.size () ) {
				throw new Exception ( "No tracks in playlist!" );
			}
			return _playlist.get ( current );
		}
	}
	
	public Playlist playlist;

	public Model () {
		playlist = new Playlist ();
	}
}