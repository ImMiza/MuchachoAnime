package utils;

import java.time.LocalDate;

public interface Art {

	public String getName();
	
	public String getImageURL();
	
	public void setImageURL(String url);
	
	public ArtType getType();
	
	public LocalDate getDateCreation();
	
	public void setDateCreation(LocalDate date);
}
