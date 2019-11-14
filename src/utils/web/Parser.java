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

public class Parser
{

	private BufferedReader	reader;
	private URL				url;

	public Parser(String url) throws IOException
	{
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
		
		for(String str : getGenres(list)) {
			System.out.println(str);
		}
		
		System.out.println(getUrlImage(list));
		System.out.println("Nombre episode: " + getNumberOfEpisode(list));
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
	
	private String getNumberOfEpisode(List<String> list) {
		
		for(String s : list) {
			if(s.contains("<span itemprop=\"numberOfEpisodes\">")) {
				System.out.println(s);
				String n = s.substring(s.lastIndexOf("\">")+2, s.lastIndexOf("<")).trim();
				return (!n.equals("?")) ? n : "-1";
			}
		}
		
		return "-2";
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

}
