import java.awt.Window.Type;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.TmdbAccount.MovieListResultsPage;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.TmdbPeople.PersonResultsPage;
import info.movito.themoviedbapi.model.Multi.MediaType;
import info.movito.themoviedbapi.TmdbSearch.*;
import info.movito.themoviedbapi.model.*;
import info.movito.themoviedbapi.model.changes.*;
import info.movito.themoviedbapi.model.config.*;
import info.movito.themoviedbapi.model.core.*;
import info.movito.themoviedbapi.model.keywords.*;
import info.movito.themoviedbapi.model.people.*;
import info.movito.themoviedbapi.model.tv.*;
import info.movito.themoviedbapi.tools.*;

public class Search {
	
	MovieModel movieModel;
	PeopleModel peopleModel;
	TvModel tvModel;
	
	TmdbPeople people;
	TmdbMovies movies;
	TmdbTV tvSeries;
	TmdbSearch search;
	
	MovieResultsPage moviePage;
	PersonResultsPage personPage;
	TvResultsPage tvPage;
	
	String prevStr;
	
	int i = 1;
	
	public Search(TmdbApi tmdbApi, MovieModel movieModel, PeopleModel peopleModel, TvModel tvModel){
		this.movieModel = movieModel;
		this.peopleModel = peopleModel;
		this.tvModel = tvModel;
		
		people = tmdbApi.getPeople();
		movies = tmdbApi.getMovies();
		tvSeries = tmdbApi.getTvSeries();
		search = tmdbApi.getSearch();
	}
	
	public void stringSearch(String searchStr){
		
		
		
		if(!searchStr.equals(prevStr)){
			i = 1;
			
			moviePage = new MovieResultsPage();
			personPage = new PersonResultsPage();
			tvPage = new TvResultsPage();
			
			movieModel.clear();
			peopleModel.clear();
			tvModel.clear();
		}
		
		prevStr = searchStr;
		
		moviePage = search.searchMovie(searchStr, null, "en", true, i);
		personPage = search.searchPerson(searchStr, true, i);
		tvPage = search.searchTv(searchStr, "en", i);
		
		i++;
		
		Iterator<MovieDb> movieIt = moviePage.iterator();
		Iterator<Person> personIt = personPage.iterator();
		Iterator<TvSeries> tvIt = tvPage.iterator();
		
		
		
		while(movieIt.hasNext()){
			MovieDb movie = movieIt.next();
			
//			MovieDb movieInfo = movies.getMovie(movie.getId(), "en", MovieMethod.credits);
			
			movieModel.add(movie);

		}
		
		while(personIt.hasNext()){
			Person person = personIt.next();
			
//			PersonPeople personInfo = people.getPersonInfo(person.getId());
			
			peopleModel.add(person);
				
		}
		
		while(tvIt.hasNext()){
			TvSeries tv = tvIt.next();
			
//			TvSeries tvInfo = tvSeries.getSeries(tv.getId(), "en");
			
			tvModel.add(tv);
				
		}
			

	}
	
	public String getMovieResults(int movieId){
		
		MovieDb movie = movies.getMovie(movieId, "en", MovieMethod.credits);
		
		String resultStr = "<html>";
		
		//Gets movie Title
		if(movie.getTitle() != ""){
			resultStr += "Title: " + movie.getTitle();
		}
		
		//Gets movie date
		if (movie.getReleaseDate() != ""){
			resultStr += "<br>Release Date: " + movie.getReleaseDate();
		} 
		
		//Gets movie director
		List<PersonCrew> crewList = movie.getCrew();
		
		Iterator<PersonCrew> crewIt = crewList.iterator();
		
		while(crewIt.hasNext()){
			PersonCrew person = crewIt.next();
			
			if (person.getJob().equals("Director")){
				
				resultStr += "<br>Director: "+ person.getName();
			}
		}
		
		//Gets Movie genre
		Iterator<Genre> genre = movie.getGenres().iterator();
    	
		if(genre.hasNext()){
			resultStr += "<br>Genre: ";
		}
		
    	while(genre.hasNext()){
    		resultStr += genre.next().getName() + " ";
    	}
    	
    	//Gets movie rating
    	if(movie.getVoteAverage() != 0){
    		resultStr += "<br>Rating: " + movie.getVoteAverage() + "/10 (" + movie.getVoteCount() + " Votes)";
    	}
		
		resultStr += "</html>";
		
		return resultStr;
	}
	
	public String getPersonResults(int personId){
		
		PersonPeople person = people.getPersonInfo(personId);
		
		String resultStr = "<html>";
		
		//get person name
		if(person.getName() != ""){
			resultStr += "Name: " + person.getName();
		}
		
		//get person birthday
		if(person.getBirthday() != ""){
			resultStr += "<br>Birthday: " + person.getBirthday();
		}
		
		//get person deathday
		if(person.getDeathday() != ""){
			resultStr += "<br>Deathday: " + person.getDeathday();
		}
		
		//get person birthplace
		if(person.getBirthplace() != ""){
			resultStr += "<br>Birthplace: " + person.getBirthplace();
		}
		
		resultStr += "</html>";
		
		return resultStr;
	}
	
	public String getTvResults(int tvIndex){
		
		TvSeries tv = tvSeries.getSeries(tvIndex, "en");
		
		String resultStr = "<html>";
		
		//get tv name
		if(tv.getOriginalName() != ""){
			resultStr += "Title: " + tv.getOriginalName();
		}
		
		//get tv first date
		if(tv.getFirstAirDate() != null){
			resultStr += "<br>First Air Date: " + tv.getFirstAirDate();
		}
		
		if(tv.getLastAirDate() != null){
			resultStr += "<br>Last Air Date: " + tv.getLastAirDate();
		}
		
		//get tv number of seasons
		if(tv.getNumberOfSeasons() != 0){
			resultStr += "<br>Number of seasons: " + tv.getNumberOfSeasons();
		}
		
		//get tv network
		Iterator<Network> network = tv.getNetworks().iterator();
		
		if(network.hasNext()){
			resultStr += "<br>Network: ";
		}
		
		while(network.hasNext()){
			resultStr += network.next().getName() + " ";
		}
		
		//Gets movie rating
    	if(tv.getVoteAverage() != 0){
    		resultStr += "<br>Rating: " + tv.getVoteAverage() + "/10 (" + tv.getVoteCount() + " Votes)";
    	}
		
		
		resultStr += "</html>";
		
		return resultStr;
	}
}
