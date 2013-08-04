package ru.guap.player.model;

import ru.guap.player.events.*;

import com.orientechnologies.orient.server.*;

import java.io.File;

public class Model {
	private OServer myServer;

	public Model () {
		myServer = null;
	}
	
	public void initDB () {
		try {
			myServer = OServerMain.create();
			myServer.startup(new File("db.config"));
			myServer.activate();
		} catch ( Exception e ) {
			System.out.println ( " |||=> ERROR: " + e.getMessage () );
		}
	}
	
	public void closeDB () {
		myServer.shutdown();
	}
}