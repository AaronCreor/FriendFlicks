package com.nebulabs.friendflix;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class Omdb {

	public static final String SEARCH_URL = "http://www.omdbapi.com/?type=movie&s=TITLE&apikey=APIKEY";
	public static final String SEARCH_BY_IMDB_URL = "http://www.omdbapi.com/?type=movie&i=IMDB&apikey=APIKEY";
	private static final String PATH_TO_CSV = "IMDbMovies.csv";
	private static final String KEY = "2f9c8f89";
	
	//this gets the data string from the omdb webstie
	private static String sendGetRequest(String requestUrl)
	{
		
		StringBuffer response = new StringBuffer();
		
		try {
		URL url = new URL(requestUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept",  "*/*");
		connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		
		InputStream stream = connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader buffer = new BufferedReader(reader);
		String line;
		while((line = buffer.readLine()) != null)
		{
			response.append(line);
		}
		buffer.close();
		connection.disconnect();
		
		}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response.toString();
	}
	//this fromats the dataString from omdb
	private static String[] omdbDataString(String id)
	{
		int CONSTANT = 1000;
		String[] movieInfoArray = new String[CONSTANT];
		String movieInfo = Omdb.searchMovieByImdb(id, KEY);
		movieInfo = movieInfo.replace("{", "").replace("}", "").replace("\"", "").replace(":", " ").replace(",", " ").replace("[", "").replace("]", "");
		movieInfoArray = movieInfo.split(" ");
		return movieInfoArray;
	}
	
	private static String searchMovieByImdb(String imdb, String key)
	{
		String requestUrl = SEARCH_BY_IMDB_URL.replaceAll("IMDB", imdb).replaceAll("APIKEY", key);
		return sendGetRequest(requestUrl);
	}

	//This gets all the imdb Ids in the csv file
	//csv file holds most popular movies in last 15 years
	private static ArrayList<String> fillImdbArray() throws IOException
	{
		BufferedReader csvReader = new BufferedReader(new FileReader(PATH_TO_CSV));
		String row;
		ArrayList<String> movies = new ArrayList<String>();
	
		while((row = csvReader.readLine()) != null)
			movies.add(row);
			
		csvReader.close();
		return movies;
	}
	//searches for the synopsis and returns it
	public static String getSynopsis(String id)
	{
		String synopsis = "";
		String[] movieInfo = omdbDataString(id);
		int j=0;
		
		while(j< movieInfo.length-1)
		{
			if(movieInfo[j].equals("Plot"))
			{
				j++;
				while(movieInfo[j].equals("Language") == false)
				{
					synopsis += movieInfo[j] + " ";
					j++;
				}
			}
			j++;
		}
		return synopsis;
	}
	//searches for the moviedatabase and returns the score
	public static String getMovieDatabase(String id)
	{
		String movieDatabase = "";
		String[] movieInfo = omdbDataString(id);
		int j=0;
		
		while(j<movieInfo.length-1)
		{
			if(movieInfo[j].equals("Movie") && movieInfo[j+1].equals("Database") && movieInfo[j+2].equals("Value"))
			{
				movieDatabase = movieInfo[j+3];
			}
			j++;
		}
		
		return movieDatabase;
	}
	//searches foe the metacritic and returns the score
	public static String getMetacritic(String id)
	{
		String metacritic = "";
		String[] movieInfo = omdbDataString(id);
		int j=0;
		
		while(j<movieInfo.length-1)
		{
			if(movieInfo[j].equals("Metacritic") && movieInfo[j+1].equals("Value"))
			{
				metacritic = movieInfo[j+2];
			}
			j++;
		}
		
		
		return metacritic;
	}
	//searches for the rotten tomato in the string and returns the score
	public static String getRottenTomato(String id)
	{
		String tomatoScore = "";
		String[] movieInfo = omdbDataString(id);
		int j=0;
		
		while(j<movieInfo.length-1)
		{
			if(movieInfo[j].equals("Rotten") && movieInfo[j+1].equals("Tomatoes"))
			{
				tomatoScore = movieInfo[j+3];
			}
			j++;
		}
		
		return tomatoScore;
	}
	//Searches for the key word https in the string and returns the poster link
	public static String getPosterLink(String id)
	{
		String posterLink = "";
		String[] movieInfo = omdbDataString(id);
		int j=0;
		
		while(j<movieInfo.length-1)
		{
			if(movieInfo[j].equals("https"))
			{
				posterLink = movieInfo[j] + ":" + movieInfo[j+1];
			}
			j++;
		}
		
		return posterLink;
	}
	//Searches for key word "Title" in the string and returns the title
	public static String getTitle(String id)
	{
		String movieName = "";
		String[] movieInfoArray = omdbDataString(id);
		int j =0;
		
		while(j<movieInfoArray.length-1)
		{
			if(movieInfoArray[j].equals("Title"))
			{
				movieName = movieInfoArray[j+1];
				while(movieInfoArray[j+2].equals("Year") == false)
				{
					movieName += " " + movieInfoArray[j+2];
					j++;
				}
			}
				
			j++;
		}
		
		return  movieName;
	}
	
	public static void main(String[] args) throws IOException {
		
		//This is to test out all functions
		//This gets the title for the first 100 movies in the cvs file
		ArrayList<String> imdbIds = new ArrayList<String>();
		imdbIds = fillImdbArray();
		for(int i=0;i<100;i++)
		{
			System.out.println(getTitle(imdbIds.get(i)));
		}
		
		
	}
	

}
