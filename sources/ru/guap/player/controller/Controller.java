package ru.guap.player.controller;

import ru.guap.player.events.*;
import ru.guap.player.model.Model;
import ru.guap.player.view.View;

public class Controller {
	public Controller ( View v, Model m ) {
		m.initDB ();
		v.mainFrame.onOpenFilesClick.bind ( new Behavior.OnFilesOpen () );
		v.mainFrame.onStop.bind ( new Behavior.OnEqOpen () );
	}
}