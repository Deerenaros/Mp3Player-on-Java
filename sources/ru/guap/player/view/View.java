package ru.guap.player.view;

import ru.guap.player.events.Event;
import ru.guap.player.events.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class View {
	public static class MainFrame extends JFrame {
		public static enum Button { PLAYPAUSE, STOP, OPENFILES, NEXT, PREV };
	
		private Container content;
		
		private final JButton play_pause;
		private final JButton stop;
		private final JButton openFiles;
		private final JButton next;
		private final JButton prev;
		
		private final Map < JButton, Button > buttons;
		
		public final JList playlist;
		
		
		public final Event event;
		
		MainFrame () {
			super ( "MP# Player" );
			
			buttons = new HashMap ();
			
			buttons.put ( stop = new JButton ( "Stop" ),			Button.STOP );
			buttons.put ( play_pause = new JButton ( "Play/Pause" ),Button.PLAYPAUSE );
			buttons.put ( openFiles = new JButton ( "Open" ),		Button.OPENFILES );
			buttons.put ( next = new JButton ( "=>" ),				Button.NEXT );
			buttons.put ( prev = new JButton ( "<=" ),				Button.PREV );
			
			playlist = new JList ();
			/*String[] listData = {};
			playlist.setListData ( listData );*/
			playlist.setMinimumSize ( new Dimension ( 1, 300 ) );
			
			event = new Event ();
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
			final Object that = this;
			
			ActionListener action = new ActionListener () {
				@Override
				public void actionPerformed ( ActionEvent e ) {
					EventArgs args = new EventArgs (
						new EventArgs.Arg ( "Type", Event.Type.BTN_CLICK ),
						new EventArgs.Arg ( "Button", buttons.get ( e.getSource () ) )
					);
					event.run ( that, args );
				}
			};
			
			for ( JButton b: buttons.keySet () ) {
				b.addActionListener ( action );
			}
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
			super ( "Equalizer" );
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