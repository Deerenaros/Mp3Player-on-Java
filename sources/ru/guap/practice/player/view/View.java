package ru.guap.practice.player.view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import ij.process.*;

import ru.guap.practice.player.generall.*;

public class View extends JFrame {
	static int WIDTH = 250;
	static int HEIGHT = 500;

	public class TopPanel extends JPanel {
		private JPanel _notes;
		private JPanel _controls;
		
		private Image _cover = null;
		private Map< String, String > _labels;
		
		private int w, h;

		TopPanel ( EventListener listener ) {
			setPreferredSize ( new Dimension ( w = View.WIDTH, h = View.HEIGHT/2 - 45 ) );
			w /= 2;
			h /= 2;
			setLayout ( new BorderLayout () );
			_labels = new HashMap ();
			_labels.put ( "title", "<Nothing>" );
			_labels.put ( "artist", "<Nothing>" );
			_labels.put ( "prev", "<=" );
			_labels.put ( "play", "|>" );
			_labels.put ( "next", "=>" );
			
			add ( _notes = new JPanel () {
				private JLabel[] jlabels;
				private final int TITLE = 0;
				private final int ARTIST = 1;
				{
					setLayout ( new BoxLayout ( this, BoxLayout.Y_AXIS ) );
					jlabels = new JLabel[ 2 ];
					add ( jlabels[ TITLE ] = new JLabel ( _labels.get ( "title" ) ) {
						{
							setFont ( getFont ().deriveFont ( 18.0f ) );
						}
					} );
					add (
						jlabels[ ARTIST ] = new JLabel ( _labels.get ( "artist" ) )
					);
					for ( JLabel l: jlabels ) {
						l.setForeground ( Color.WHITE );
					}
					setOpaque ( false );
				}
				@Override
				public void updateUI () {
					super.updateUI ();
					if ( jlabels != null ) {
						jlabels[ TITLE ].setText ( _labels.get ( "title" ) );
						jlabels[ ARTIST ].setText ( _labels.get ( "artist" ) );
					}
				}
				@Override
				public void paintComponent ( Graphics g ) {
					g.setColor ( new Color ( 0, 0, 0, 125 ) );
					g.fillRect ( 0, 0, getWidth (), getHeight () );
				}
			}, BorderLayout.NORTH );
			add ( _controls = new JPanel () {
				JButton[] jbuttons = new JButton[ 3 ];
				private final int PREV = 0;
				private final int PLAY = 1;
				private final int NEXT = 2;
				{
					add ( new JButton ( _labels.get ( "prev" ) ) {
						{
							setActionCommand ( "prev" );
							addActionListener ( (ActionListener)listener );
						}
					} );
					add ( new JButton ( _labels.get ( "play" ) ) {
						{
							setActionCommand ( "play" );
							addActionListener ( (ActionListener)listener );
						}
					} );
					add ( new JButton ( _labels.get ( "next" ) ) {
						{
							setActionCommand ( "next" );
							addActionListener ( (ActionListener)listener );
						}
					} );
					setOpaque ( false );
				}
				@Override
				public void updateUI () {
					super.updateUI ();
					if ( jbuttons != null ) {
						jbuttons[ PREV ].setText ( _labels.get ( "prev" ) );
						jbuttons[ PLAY ].setText ( _labels.get ( "play" ) );
						jbuttons[ NEXT ].setText ( _labels.get ( "next" ) );
					}
				}
				@Override
				public void paintComponent ( Graphics g ) {
					g.setColor ( new Color ( 0, 0, 0, 125 ) );
					g.fillRect ( 0, 0, getWidth (), getHeight () );
				}
			}, BorderLayout.SOUTH );
		}
		
		public void updateNotes ( Track track ) {
			try {
				_labels.put ( "title", track.getTitle () );
				_labels.put ( "artist", track.getArtist () );
				_cover = track.getCover ();
				_notes.updateUI ();
				repaint ();
			} catch ( Exception e ) {
				System.out.println ( "ERROR: " + e.getMessage () );
			}
		}
		
		float[] fft;
		public void setFFT ( float[] _fft_ ) {
			fft = _fft_;
		}
		@Override
		public void paintComponent ( Graphics g ) {
			super.paintComponent ( g );
			g.clearRect ( 0, 0, w*2, h*2 );
			if ( _cover != null ) {
				ColorProcessor proc = new ColorProcessor ( _cover );
				byte[] hue = new byte[ proc.getWidth()*proc.getHeight() ];
				byte[] s = new byte[ proc.getWidth()*proc.getHeight() ];
				byte[] b = new byte[ proc.getWidth()*proc.getHeight() ];
				proc.getHSB ( hue, s, b );
				for ( int i = 0; i < proc.getWidth()*proc.getHeight(); i++ ) {
					//b[ i ] = (byte)Math.min ( b[ i ] - 50, -128 );
					//b[ i ] = (byte)Math.min ( (int)b[ i ] - 20, -128 );
					//s[ i ] = (byte)Math.min ( (int)s[ i ] - 35, -128 );
				}
				proc.setHSB ( hue, s, b );
				//proc.blurGaussian ( 1.8 );
				g.drawImage ( proc.createImage (), 0, 0, 2*w, 2*h, new Color ( 0, 0, 0, 255 ), this );
			}
			g.setColor ( Color.WHITE );
			//TO MUCH STREET MAGIC HERE, be dangerous
			if ( fft != null ) {
				for ( int i = 2; i < fft.length; i += 2 ) {
					g.drawLine (
						i*w / ( fft.length + 5 ) + w / 2,
						5*h / 3,
						i*w / ( fft.length + 5) + w / 2,
						5*h / 3 - (int)( 25*Math.sqrt ( fft[i]*fft[i] + fft[i+1]*fft[i+1] ) )
					);
				}
			}
		}
	}
	
	/**
	 * Some street magic here
	 */
	public class Entries extends JPanel {
		private class Entry extends JPanel {
			public static final int HEIGHT = 80;
		
			private Track _info;
			
			Entry ( Track info ) {
				_info = info;
				setLayout ( new BorderLayout () );
				setBorder ( BorderFactory.createTitledBorder (
					BorderFactory.createLineBorder ( Color.black ),
					info.getArtist () )
				);
				JLabel title = new JLabel ( info.getTitle () );
				title.setFont ( title.getFont ().deriveFont ( 18.0f ) );
				add ( title, BorderLayout.CENTER );
				add ( new JLabel ( info.getAlbum () ), BorderLayout.SOUTH );
			}
			@Override
			public void paintComponent ( Graphics g ) {
				super.paintComponent ( g );
				g.clearRect ( 0, 0, getWidth (), getHeight () );
			}
		}
		
		JScrollPane scroll;
		JPanel list;
		JPanel bottomButtons;
		
		final int height = View.HEIGHT/2;
		final int scrollSpeed = 20;
		
		Entries ( EventListener listner ) {
			final int n = 2;
			
			list = new JPanel () {
				@Override
				public void paintComponent ( Graphics g ) {
					super.paintComponent ( g );
					g.clearRect ( 0, 0, getWidth (), getHeight () );
				}
			};
			list.setBorder ( BorderFactory.createLineBorder ( Color.red ) );
			list.setPreferredSize( new Dimension ( 0, n*Entry.HEIGHT ) );
			list.setLayout ( new GridLayout ( 0, 1, 5, 5 ) );
			
			bottomButtons = new JPanel ();
			JButton b = new JButton ( "open files" );
			b.setActionCommand ( "open" );
			b.addActionListener ( (ActionListener)listner );
			bottomButtons.add ( b );

			scroll = new JScrollPane ( list );
			scroll.getVerticalScrollBar ().setUnitIncrement ( scrollSpeed ); // It's better to make keenetic scroll...

			setLayout ( new BorderLayout () );
			add ( scroll, BorderLayout.CENTER );
			add ( bottomButtons, BorderLayout.SOUTH );
			setPreferredSize ( new Dimension ( WIDTH, height ) );
		}
		
		public void pullEntries ( Track[] tracks ) {
			list.removeAll ();
			list.setPreferredSize ( new Dimension ( 0, tracks.length*Entry.HEIGHT ) );
			for ( Track t: tracks ) {
				list.add ( new Entry ( t ) );
			}
			/*list.validate ();
			list.repaint ();
			scroll.validate ();
			scroll.repaint ();*/
			validate ();
			repaint ();
		}
	}

	
	public TopPanel topPanel;
	public Entries entries;
	
	public View ( EventListener listner ) {
		super ( "Just a player!" );
		SwingUtilities.invokeLater ( new Runnable() {
            @Override
            public void run () {
				//UIManager.getLookAndFeelDefaults ().put ( "Panel.background", new Color ( 255, 0, 0, 128 ) );
                setSize ( WIDTH, HEIGHT );
				setResizable( false );
				setLayout ( new BorderLayout () );
				add ( topPanel = new TopPanel ( listner ), BorderLayout.NORTH );
				add ( entries  = new Entries  ( listner ), BorderLayout.SOUTH );
				setDefaultCloseOperation ( EXIT_ON_CLOSE );
				
				setVisible ( true );
            }
        } );
	}
}