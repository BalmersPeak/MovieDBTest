import java.util.Iterator;

import info.movito.themoviedbapi.*;
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
	
	MultiListResultsPage resultPage;
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
	
	public void Multi(String searchStr){
		
		
		
		if(!searchStr.equals(prevStr)){
			i = 1;
			resultPage = new MultiListResultsPage();
			
			movieModel.clear();
			peopleModel.clear();
			tvModel.clear();
		}
		
		prevStr = searchStr;
		
		resultPage = search.searchMulti(searchStr, "en", i);
		i++;
		
		Iterator<Multi> iterator = resultPage.iterator();
		
		while(iterator.hasNext())
		{
			Multi multi = iterator.next();
			if(multi.getMediaType() == MediaType.PERSON)
			{
				Person person = (Person) multi;
				
				Person personInfo = people.getPersonInfo(person.getId());
				
				peopleModel.add(personInfo);
				
			}
			else if(multi.getMediaType() == MediaType.MOVIE)
			{
				MovieDb movie = (MovieDb) multi;
				
				MovieDb movieInfo = movies.getMovie(movie.getId(), "en");
				
				movieModel.add(movieInfo);
			}
			else if(multi.getMediaType() == MediaType.TV_SERIES)
			{
				TvSeries tv = (TvSeries) multi;
				
				TvSeries tvInfo = tvSeries.getSeries(tv.getId(), "en");
				
				tvModel.add(tvInfo);
			}
		}
	}
}
