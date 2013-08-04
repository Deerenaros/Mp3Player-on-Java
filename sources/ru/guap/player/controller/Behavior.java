package ru.guap.player.controller;

import ru.guap.player.events.*;
import ru.guap.player.view.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

class Behavior {
	public static class OnFilesOpen implements IEvent {
		@Override
		public void run ( Object sender, EventArgs args ) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter ( new FileNameExtensionFilter( "MP3 Music", "mp3" ) );
			if ( chooser.showOpenDialog ( null ) == JFileChooser.APPROVE_OPTION ) {
				String[] listData = {
					chooser.getSelectedFile().getName()
				};
				( ( View.MainFrame ) sender ).playlist.setListData ( listData );
			}
		}
	}
	
	public static class OnEqOpen implements IEvent {
		@Override                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
		public void run ( Object sender, EventArgs args ) {
			( ( View.MainFrame ) sender ).v.equalizerDialog.setVisible ( true );
		}
	}
}