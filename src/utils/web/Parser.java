package utils.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import exceptions.ParserNotNautiljonException;

public class Parser
{

	private BufferedReader	reader;
	private URL				url;

	private String originalTitle;
	private String alternativeTitle;
	private List<String> genres;
	private String imageURL;
	private String linkURL;
	private int numberOfEpisode;
	
	public Parser(String url) throws IOException, ParserNotNautiljonException
	{
		if(!url.contains("www.nautiljon.com"))
			throw new ParserNotNautiljonException();
		
		this.url = new URL(url);
		this.reader = getBufferedReader(this.url);
		List<String> list = new ArrayList<String>();
		String line = "";
		boolean start = false;
		while ((line = this.reader.readLine()) != null && !line.contains("<div class=\"top_bloc\">"))
		{
			if(line.contains("<div class=\"infosFicheTop\">"))
				start = true;
			
			if(start)
				list.addAll(Arrays.asList(line.replace("><", ">\n<").trim().split("\n")));
		}
		
		this.originalTitle = getOriginalTitle(list);
		this.alternativeTitle = getAlternativeTitle(list);
		this.genres = getGenres(list);
		this.imageURL = getUrlImage(list);
		this.numberOfEpisode = getNumberOfEpisode(list);
		this.linkURL = url;
		
		this.reader.close();
	}

	private List<String> getGenres(List<String> list) {
		List<String> genres = new ArrayList<>();
		
		for(String s : list) {
			if(s.contains("<span itemprop=\"genre\">")) {
				genres.add(s.substring(s.indexOf(">") + 1, s.lastIndexOf("<")));
			}
		}
		
		return genres;
	}
	
	private String getUrlImage(List<String> list) {
		String url = "https://www.nautiljon.com";
		
		for(String s : list) {
			if(s.contains("itemprop=\"image\"")) {
				String str = s.split(" ")[1];
				return url + str.substring(str.indexOf("/"), str.lastIndexOf("\""));
			}
		}
		
		return url;
	}
	
	private String getOriginalTitle(List<String> list) {
		String title = "";
		
		for(String s : list) {
			if(s.contains("<span class=\"bold\">Titre original : </span>")) {
				String str = s.replace("<span class=\"bold\">Titre original : </span>", "").replace("</li>", "").trim();
				return (str.contains("/")) ? str.split("/")[0] : str;
			}
		}
		
		return title;
	}
	
	private String getAlternativeTitle(List<String> list) {
		String title = "";
		
		for(String s : list) {
			if(s.contains("<span class=\"bold\">Titre alternatif : </span>")) {
				String str = s.replace("<span class=\"bold\">Titre alternatif : </span>", "").replace("</li>", "");
				str = str.replace("<span itemprop=\"alternateName\">", "").replace("</span>", "");
				return (str.contains("/")) ? str.split("/")[0] : str;
			}
		}
		
		return title;
	}
	
	private int getNumberOfEpisode(List<String> list) {
		
		for(String s : list) {
			if(s.contains("<span itemprop=\"numberOfEpisodes\">")) {
				String n = s.substring(s.lastIndexOf("\">")+2, s.lastIndexOf("<"));
				n = n.replace("</span>", "").trim();
				return (n.contains("x")) ? Integer.parseInt(n.split("x")[0].trim()) : -1;
			}
		}
		
		return -1;
	}

	private BufferedReader getBufferedReader(URL url) {
		TrustManager[] trustAllCerts = new TrustManager[]{
		        new X509TrustManager() {
					
					@Override
					public X509Certificate[] getAcceptedIssuers() {return null;}

					@Override
					public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
					{}

					@Override
					public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
					{}
				}
		};

		try 
		{
		    SSLContext sc = SSLContext.getInstance("SSL");
		    sc.init(null, trustAllCerts, new java.security.SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		    HttpsURLConnection http = (HttpsURLConnection) this.url.openConnection();
		    return new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
		} 
		catch (Exception e) 
		{
		    System.out.println(e.getMessage());
		    return null;
		}
	}

	public String getOriginalTitle()
	{
		return originalTitle;
	}

	public String getAlternativeTitle()
	{
		return alternativeTitle;
	}

	public List<String> getGenres()
	{
		return genres;
	}

	public String getImageURL()
	{
		return imageURL;
	}

	public int getNumberOfEpisode()
	{
		return numberOfEpisode;
	}

	public String getLinkURL()
	{
		return linkURL;
	}
}
