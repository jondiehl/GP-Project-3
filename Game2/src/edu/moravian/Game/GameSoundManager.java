package edu.moravian.Game;

import java.util.ArrayList;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;


public class GameSoundManager {
	
	private ArrayList<Music> playlist = new ArrayList<Music>();
	private Music commander;
	private Music quickTips;
	private int playlistCount;
	private Boolean isPlaying;
	
	public GameSoundManager() throws SlickException {
		commander = new Music("res2/Commander.wav");
	    
	    playlist.add(commander);
	    
	    playlistCount = -1;
	}
	
	public void playNextSong() {
		if (playlistCount >= 2) {
			playlistCount = -1;
		}
		playlistCount++;
		playlist.get(playlistCount).play();
	}
	
	public void pauseSong() {
		playlist.get(playlistCount).pause();
	}
	
	public void resumeSong() {
		playlist.get(playlistCount).resume();
	}
	
	public void playSound() {
		quickTips.play();
	}
	
	public Boolean isPlaying() {
		if(playlist.get(playlistCount).playing()) {
			isPlaying = true;
		}
		else {
			isPlaying = false;
		}
		return isPlaying;
	}

}
