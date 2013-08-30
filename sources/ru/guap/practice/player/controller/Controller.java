package ru.guap.practice.player.controller;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

import ru.guap.practice.player.model.*;
import ru.guap.practice.player.view.*;
import ru.guap.practice.player.generall.*;

import javazoom.jlgui.basicplayer.*;
import edu.emory.mathcs.jtransforms.fft.*;

class Controller implements ActionListener, BasicPlayerListener {
	View view;
	Model model;
	
	BasicPlayer player;
	
	public Controller () {
		view = new View ( this );
		model = new Model ();
		player = new BasicPlayer ();
		player.addBasicPlayerListener ( this );
	}
	
	public static void main ( String[] args ) {
		new Controller ();
	}
	
	private void openFiles () {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter ( new FileNameExtensionFilter( "Music", "mp3", "ogg", "flac" ) );
		chooser.setMultiSelectionEnabled ( true );
		if ( chooser.showOpenDialog ( null ) == JFileChooser.APPROVE_OPTION ) {
			File[] files = chooser.getSelectedFiles();
			try {
				for ( File f: files ) {
						model.playlist.add ( f );
				}
			} catch ( Exception e ) {}
			
			for ( Track t: model.playlist.getTracks () ) {
				System.out.println ( t );
			}
			
			view.entries.pullEntries ( model.playlist.getTracks () );
		}
	}
	
	@Override
	public void actionPerformed ( ActionEvent e ) {
		if ( e.getSource () instanceof JButton ) {
			try {
				switch ( e.getActionCommand() ) {
					case "play":
						switch ( player.getStatus () ) {
							case BasicPlayer.STOPPED:
								player.open ( model.playlist.getCurrent ().getFile () );
							case BasicPlayer.PAUSED:
								player.play ();
								break;
							case BasicPlayer.PLAYING:
								player.pause ();
								break;
						}
						break;
					case "open":
						openFiles ();
						view.topPanel.updateNotes ( model.playlist.getCurrent () );
						if ( player.getStatus () != player.PLAYING ) {
							player.open ( model.playlist.getCurrent ().getFile () );
							player.play ();
						}
						break;
					case "next":
						model.playlist.next ();
						view.topPanel.updateNotes ( model.playlist.getCurrent () );
						player.open ( model.playlist.getCurrent ().getFile () );
						player.play ();
						break;
					case "prev":
						model.playlist.prev ();
						view.topPanel.updateNotes ( model.playlist.getCurrent () );
						player.open ( model.playlist.getCurrent ().getFile () );
						player.play ();
						break;
					default:
						System.out.println ( "mur-r-r-r..." );
						break;
				}
			} catch ( Exception ex ) {
				ex.printStackTrace ();
			}
		}
	}
	
	public void opened ( Object stream, Map properties ) {
		System.out.println ( properties );
		/*try {
			//player.play ();
		} catch ( Exception e ) {
			System.out.println ( "ERROR: " + e.getMessage () );
		}*/
	}
	
	public void progress ( int bytesread, long microseconds, byte[] pcmdata, Map properties ) {
		try {
			//System.out.println ( microseconds/1000000 );
			float[] _fft_ = new float[ pcmdata.length*2 ];
			for ( int i = 0; i < pcmdata.length*2; i++ ) {
				if ( i%2 == 0 ) {
					int d = (int)pcmdata[ i/2 ];
					_fft_[ i ] = ( d + 128 ) / 255.0f;
				} else {
					_fft_[ i ] = 0;
				}
			}
			FloatFFT_1D fft = new FloatFFT_1D ( 40 );
			fft.complexForward ( _fft_ );
			view.topPanel.setFFT ( _fft_ = Arrays.copyOfRange ( _fft_, 0, 40 ) );
			/*for ( int i = 0; i < _fft_.length; i += 2 ) {
				System.out.println ( _fft_[ i ] * _fft_[ i ] + _fft_[ i + 1 ] *_fft_[ i + 1 ] );
			} System.out.println ( "-------------------" );*/
			view.topPanel.repaint ();
		} catch ( Throwable e ) {
			e.printStackTrace ();
		}
	}
	
	public void stateUpdated ( BasicPlayerEvent event ) {
		try {
			switch ( event.getCode () ) {
				case BasicPlayerEvent.STOPPED:
					model.playlist.next ();
					view.topPanel.updateNotes ( model.playlist.getCurrent () );
					player.open ( model.playlist.getCurrent ().getFile () );
					player.play ();
					break;
				default:
					break;
			}
		} catch ( Exception e ) {
			System.out.println ( "ERROR: " + e.getMessage () );
		}
	}
	
	public void setController ( BasicController controller ) {
	}
}