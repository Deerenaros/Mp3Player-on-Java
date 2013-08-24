package ru.guap.player.controller;

import ru.guap.player.events.*;
import ru.guap.player.model.*;
import ru.guap.player.view.*;

public class Controller {
	public Controller ( View v, Model m ) {
		m.initDB ();
		Behavior.view = v; Behavior.model = m;
		Behavior.player = new BasicPlayer();
		
		v.mainFrame.event.bind ( new Behavior.MainFrameEvents () );
		
		/*v.mainFrame.onPlayPauseClick.bind ( new Behavior.OnPlayPause () );
		v.mainFrame.onStop.bind ( new Behavior.OnEqOpen () );
		v.mainFrame.onOpenFilesClick.bind ( new Behavior.OnFilesOpen () );
		v.mainFrame.onNextClick.bind ( new Behavior.OnNextClick () );
		v.mainFrame.onPrevClick.bind ( new Behavior.OnPrevClick () );*/
	}
}