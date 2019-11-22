package utils;

import java.time.LocalDate;

import net.dv8tion.jda.api.EmbedBuilder;

public interface Art {

	public String getName();
	
	public String getImageURL();
	
	public String getSourceURL();
	
	public void setImageURL(String url);
	
	public ArtType getType();
	
	public LocalDate getDateCreation();
	
	public void setDateCreation(LocalDate date);
	
	public EmbedBuilder createEmbed();
	
	
}
