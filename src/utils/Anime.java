package utils;

import java.time.LocalDate;

import net.dv8tion.jda.api.EmbedBuilder;

public class Anime implements Art{

	private String name;
	private String imageURL;
	
	private int numberOfEpisode;
	private int currentEpisode;
	private int season;

	private LocalDate creation;
	
	private boolean finish;
	private boolean start;
	
	public Anime(String name, String imageURL, int numberOfEpisode, int season, LocalDate creation) {
		this.name = name;
		this.imageURL = imageURL;
		this.numberOfEpisode = numberOfEpisode;
		this.currentEpisode = 1;
		this.season = season;
		this.creation = creation;
		this.finish = false;
		
		if(LocalDate.now().isAfter(creation) || LocalDate.now().isEqual(creation)) this.start = true;
		else this.start = false;
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
	
	public boolean isFinish()
	{
		return finish;
	}
	
	public void setFinish(boolean finish)
	{
		this.finish = finish;
	}
	
	public boolean isStart()
	{
		return start;
	}
	
	public void setStart(boolean start)
	{
		this.start = start;
	}
	
	public boolean isInProgress() {
		return start == true && finish == false;
	}
	
	@Override
	public EmbedBuilder createEmbed()
	{
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle(getName());
		builder.setColor(0x33ccff);
		
		String description = "Etat de l'anime : ";
		if(isFinish())
			description += "Fini";
		else if(isInProgress())
			description += "En cours";
		else
			description += "Pas encore sorti";
		
		description += "\nSortie le :" + ((isStart()) ? creation.toString() : "Pas encore sortie");
		builder.setDescription(description);
		builder.addField("Episode en cours", ""+getCurrentEpisode(), true);
		builder.addField("Nombre d'episode", ""+getNumberOfEpisode(), true);
		builder.addField("Saison", ""+getSeason(), true);
		
		if(!getImageURL().isEmpty())
			builder.setImage(getImageURL());
		
		return builder;
	}

}
