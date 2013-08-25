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
		static void openFiles ( View.MainFrame mframe ) {
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
				
				mframe.playlist.setListData ( model.playlist.getList () );
			}
		}
		
		static void stop ( View.MainFrame mframe ) {
			try {
				player.stop ();
			} catch ( Exception e ) {}
		}
		
		static void playpause ( View.MainFrame mframe ) {
			try {
				if ( model.playlist != null && model.playlist.getCurrent () != null ) {
					if ( !player.isPlaying () ) {
						player.setTrack ( model.playlist.getCurrent () );
						player.play ();
					} else {
						player.pause ();
					}
				}
			} catch ( Exception e ) {
				System.out.println ( e.getMessage () );
			}
		}
		
		static void next ( View.MainFrame mframe ) {
			try {
				model.playlist.next ();
				player.setTrack ( model.playlist.getCurrent () );
				player.play ();
			} catch ( Exception e ) {}
		}
		
		static void prev ( View.MainFrame mframe ) {
			try {
				model.playlist.previous ();
				player.setTrack ( model.playlist.getCurrent () );
				player.play ();
			} catch ( Exception e ) {}
		}

		@Override
		public void run ( Object sender, EventArgs args ) {
			switch ( (Event.Type) args.get ( "Type" ) ) {
				case BTN_CLICK:
					switch ( (View.MainFrame.Button) args.get ( "Button" ) ) {
						case PLAYPAUSE:
							playpause ( (View.MainFrame)sender );
							break;
						case STOP:
							stop ( (View.MainFrame)sender );
							break;
						case OPENFILES:
							openFiles ( (View.MainFrame)sender );
							break;
						case NEXT:
							next ( (View.MainFrame)sender );
							break;
						case PREV:
							prev ( (View.MainFrame)sender );
							break;
						default:
							break;
					}
					break;
				default:
					break;
			}
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