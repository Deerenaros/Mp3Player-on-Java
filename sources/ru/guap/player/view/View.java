package ru.guap.player.view;

import ru.guap.player.events.Event;
import ru.guap.player.events.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View {
	public class MainFrame extends JFrame {
		private Container content;
		
		private final JButton play_pause;
		private final JButton stop;
		private final JButton openFiles;
		private final JButton next;
		private final JButton prev;
		
		public final JList playlist;
		
		public final Event onPlayPauseClick;
		public final Event onStop;
		public final Event onOpenFilesClick;
		public final Event onNextClick;
		public final Event onPrevClick;
		
		MainFrame () {
			super ( "MP# Player" );
			
			stop = new JButton ( "Stop" );
			play_pause = new JButton ( "Play/Pause" );
			openFiles = new JButton ( "Open" );
			next = new JButton ( "=>" );
			prev = new JButton ( "<=" );
			
			playlist = new JList ();
			String[] listData =
			{
				"Item 1",
				"Item 2",
				"Item 3",
				"Item 4"
			};
			playlist.setListData ( listData );
			playlist.setMinimumSize ( new Dimension ( 1, 300 ) );
			
			onPlayPauseClick = new Event ();
			onStop = new Event ();
			onOpenFilesClick = new Event ();
			onNextClick = new Event ();
			onPrevClick = new Event ();
		}
		
		public void makeGUI () {
			setDefaultCloseOperation ( EXIT_ON_CLOSE );
			content = getContentPane ();
			content.setLayout ( new BorderLayout () );
			
			JScrollPane scrollPane = new JScrollPane ( playlist );
			content.add ( scrollPane, BorderLayout.SOUTH );
			
			JPanel controls = new JPanel ( new BorderLayout () );
			controls.add ( stop, BorderLayout.SOUTH );
			controls.add ( play_pause, BorderLayout.CENTER );
			controls.add ( openFiles, BorderLayout.NORTH );
			controls.add ( next, BorderLayout.EAST );
			controls.add ( prev, BorderLayout.WEST );
			content.add ( controls, BorderLayout.NORTH );
			
			pack();
			setVisible ( true );
		}
		
		public void registerEvents () {
			final Object _this = this;
			
			openFiles.addActionListener ( new ActionListener () {
				@Override
				public void actionPerformed ( ActionEvent e ) {
					onOpenFilesClick.run( _this, EventArgs.Empty );
				}
			} );
			stop.addActionListener ( new ActionListener () {
				@Override
				public void actionPerformed ( ActionEvent e ) {
					onStop.run( _this, EventArgs.Empty );
				}
			} );
			play_pause.addActionListener ( new ActionListener () {
				@Override
				public void actionPerformed ( ActionEvent e ) {
					onPlayPauseClick.run( _this, EventArgs.Empty );
				}
			} );;
			next.addActionListener ( new ActionListener () {
				@Override
				public void actionPerformed ( ActionEvent e ) {
					onNextClick.run( _this, EventArgs.Empty );
				}
			} );;
			prev.addActionListener ( new ActionListener () {
				@Override
				public void actionPerformed ( ActionEvent e ) {
					onPrevClick.run( _this, EventArgs.Empty );
				}
			} );;
		}
	}
	
	public class LibraryFrame extends JFrame {
	
		LibraryFrame () {
		}
		
		public void makeGUI () {
		}
		
		public void registerEvents () {
		}
	}
	
	public class EqualizerDialog extends JFrame {
		private Container content;
	
		EqualizerDialog () {
			super ("");
		}
		
		public void makeGUI () {
			setSize ( 300, 180 );
			setDefaultCloseOperation ( DISPOSE_ON_CLOSE );
		}
		
		public void registerEvents () {
		}
		
		public void paint ( Graphics g ) {
			g.drawLine ( 10, 10, 290, 170 );
		}
	}
	
	public final MainFrame mainFrame;
	public final LibraryFrame libraryFrame;
	public final EqualizerDialog equalizerDialog;

	public View () {
		mainFrame = new MainFrame (  );
		libraryFrame = new LibraryFrame (  );
		equalizerDialog = new EqualizerDialog (  );
		
		javax.swing.SwingUtilities.invokeLater ( new Runnable() {
			@Override
			public void run() {
				mainFrame.makeGUI ();
				mainFrame.registerEvents ();
				
				libraryFrame.makeGUI ();
				libraryFrame.registerEvents ();
				
				equalizerDialog.makeGUI ();
				equalizerDialog.registerEvents ();
			}
		} );
	}
}