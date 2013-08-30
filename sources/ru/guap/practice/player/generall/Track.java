package ru.guap.practice.player.generall;

import java.io.File;
import java.awt.Image;

import org.jaudiotagger.tag.*;
import org.jaudiotagger.audio.*;

public class Track {
	private File _file;
	private AudioFile _audio;
	private String _title;
	private String _artist;
	private String _album;
	
	public Track ( File f ) {
		try {
			java.util.logging.LogManager.getLogManager().reset();
			_audio = AudioFileIO.read ( _file = f );
			Tag tag = _audio.getTag ();
			_title = tag.getFirst ( FieldKey.TITLE );
			_artist = tag.getFirst ( FieldKey.ARTIST );
			_album = tag.getFirst ( FieldKey.ALBUM );
		} catch ( Exception e ) {
			System.out.println ( "ERROR: " + e.getMessage () );
		}
	}
	
	public String getTitle () {
		return _title;
	}
	
	public String getArtist () {
		return _artist;
	}
	
	public String getAlbum () {
		return _album;
	}
	
	public File getFile () {
		return _file;
	}
	
	public Image getCover () throws Exception {
		return _audio.getTag ().getFirstArtwork ().getImage ();
	}
	
	@Override
	public String toString () {
		return _title + " (" + _artist + " - " + _album + ")";
	}
}