package ru.guap.player.controller;

import ru.guap.player.events.*;
import ru.guap.player.model.*;
import ru.guap.player.view.*;

public class Controller {
	public Controller ( View v, Model m ) {
		m.initDB ();
		Behavior.view = v; Behavior.model = m;
		v.mainFrame.onOpenFilesClick.bind ( new Behavior.OnFilesOpen () );
		v.mainFrame.onStop.bind ( new Behavior.OnEqOpen () );
	}
}