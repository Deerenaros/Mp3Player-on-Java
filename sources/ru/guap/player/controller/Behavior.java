package ru.guap.player.controller;

import ru.guap.player.events.*;
import ru.guap.player.model.*;
import ru.guap.player.view.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.util.*;

class Behavior {
	static View view;
	static Model model;
	static BasicPlayer player;

	static class MainFrameEvents implements IEvent {
		@Override
		public void run ( Object sender, EventArgs args ) {
			System.out.println ( args );
		}
	}
	
	/*static class OnFilesOpen implements IEvent {
		@Override
		public void run ( Object sender, EventArgs args ) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter ( new FileNameExtensionFilter( "MP3 Music", "mp3" ) );
			chooser.setMultiSelectionEnabled ( true );
			if ( chooser.showOpenDialog ( null ) == JFileChooser.APPROVE_OPTION ) {
				File[] files = chooser.getSelectedFiles();
				List < String > lst = new LinkedList ();
				for ( File f: files ) {
					model.playlist.add ( f );
					lst.add ( f.getName () );
				}
				( ( View.MainFrame ) sender ).playlist.setListData ( lst.toArray () );
			}
		}
	}
	
	static class OnEqOpen implements IEvent {
		@Override                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
		public void run ( Object sender, EventArgs args ) {
			view.equalizerDialog.setVisible ( true );
		}
	}
	
	static class OnPlayPause implements IEvent {
		@Override
		public void run ( Object sender, EventArgs args ) {
			try {
				if ( model.playlist != null && model.playlist.getCurrent () != null ) {
					if ( !player.isPlaying () ) {
						player.play ( model.playlist.getCurrent () );
					} else {
						player.pause ();
					}
				}
			} catch ( Exception e ) {
				System.out.println ( e.getMessage () );
			}
		}
	}
	
	static class OnNextClick implements IEvent {
		@Override
		public void run ( Object sender, EventArgs args ) {
			
		}
	}
	
	static class OnPrevClick implements IEvent {
		@Override
		public void run ( Object sender, EventArgs args ) {
			
		}
	}*/
}