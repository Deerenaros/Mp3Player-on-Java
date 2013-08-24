package ru.guap.player.model;

import ru.guap.player.events.*;

import com.orientechnologies.orient.server.*;

import java.io.File;

public class Model {
	private OServer myServer;
	
	public Playlist playlist;

	public Model () {
		myServer = null;
		playlist = new Playlist ();
	}
	
	public void initDB () {
		if ( myServer == null ) {
			try {
				myServer = OServerMain.create();
				myServer.startup ( new File ( "db.config" ) );
				myServer.activate ();
			} catch ( Exception e ) {
				System.out.println ( " |||=> ERROR: " + e.getMessage () );
			}
		}
	}
	
	public void closeDB () {
		myServer.shutdown();
		myServer = null;
	}
}