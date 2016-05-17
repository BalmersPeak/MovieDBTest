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

public class Test {
	public static void main(String[] args){
		TmdbApi tmdbApi = new TmdbApi("811ffa781385ed56e1ec64c193eb93f4");
		
		TmdbPeople people = tmdbApi.getPeople();
		
		TmdbMovies movies = tmdbApi.getMovies();

		TmdbKeywords keywords = tmdbApi.getKeywords();
		
		TmdbTV tvSeries = tmdbApi.getTvSeries();
		
		TmdbSearch search = tmdbApi.getSearch();
		
//		System.out.println(resultsPage.getResults());
//		System.out.println(keyword.toString());
		
		MultiListResultsPage resultPage = search.searchMulti("Steve", "en", 0);
		
		System.out.println(resultPage.getResults());
		
		Iterator<Multi> iterator = resultPage.iterator();
		
		while(iterator.hasNext())
		{
			Multi multi = iterator.next();
			if(multi.getMediaType() == MediaType.PERSON)
			{
				PersonPeople person = (PersonPeople) multi;
				
				PersonPeople personInfo = people.getPersonInfo(person.getId());
				
				System.out.println(personInfo.getName());
				System.out.println(personInfo.getBirthday());
//				System.out.println(personInfo.getBiography());
				System.out.println(personInfo.getBirthplace());
				
			}
			else if(multi.getMediaType() == MediaType.MOVIE)
			{
				MovieDb movie = (MovieDb) multi;
				
				MovieDb movieInfo = movies.getMovie(movie.getId(), "en");
				
				System.out.println(movieInfo.getTitle());
				System.out.println(movieInfo.getImdbID());
			}
			else if(multi.getMediaType() == MediaType.TV_SERIES)
			{
				TvSeries tv = (TvSeries) multi;
				
				TvSeries tvInfo = tvSeries.getSeries(tv.getId(), "en");
				
				System.out.println(tvInfo.getName());
				System.out.println(tvInfo.getNumberOfSeasons());
			}
		}

//		MovieDb movie = movies.getMovie(5000, "en");
//		
//		System.out.println(movie.getTitle());
//		System.out.println(movie.getReleaseDate());
//		System.out.println(movie.getRuntime());
//		System.out.println(movie.getKeywords());
//		
//		
//		
//		TmdbPeople people = tmdbApi.getPeople();
//		
//		PersonPeople person = people.getPersonInfo(1);
//		
//		System.out.println(person.getName());
//		System.out.println(person.getBirthday());
	}
}
