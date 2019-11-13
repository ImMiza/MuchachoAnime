package utils;

import java.time.LocalDate;

public class Anime implements Art{

	private String name;
	private String imageURL;
	
	private int numberOfEpisode;
	private int currentEpisode;
	private int season;

	private LocalDate creation;
	
	public Anime(String name, String imageURL, int numberOfEpisode, int season, LocalDate creation) {
		this.name = name;
		this.imageURL = imageURL;
		this.numberOfEpisode = numberOfEpisode;
		this.currentEpisode = 1;
		this.season = season;
		this.creation = creation;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getImageURL() {
		return imageURL;
	}

	@Override
	public void setImageURL(String url) {
		imageURL = url;
	}

	@Override
	public ArtType getType() {
		return ArtType.ANIME;
	}

	public int getNumberOfEpisode() {
		return numberOfEpisode;
	}

	public void setNumberOfEpisode(int n) {
		numberOfEpisode = n;
	}
	
	public int getCurrentEpisode() {
		return currentEpisode;
	}
	
	public void setCurrentEpisode(int n) {
		currentEpisode = n;
	}
	
	public int getSeason() {
		return season;
	}
	
	public void setSeason(int season) {
		this.season = season;
	}

	@Override
	public LocalDate getDateCreation() {
		return creation;
	}

	@Override
	public void setDateCreation(LocalDate date) {
		creation = date;
	}

}
