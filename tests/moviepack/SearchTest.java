package moviepack;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.TmdbTV;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.people.PersonPeople;
import info.movito.themoviedbapi.model.tv.TvSeries;

public class SearchTest {


    
    
    TmdbApi tmdbApi = new TmdbApi("811ffa781385ed56e1ec64c193eb93f4");

    TmdbMovies movie = tmdbApi.getMovies();
    TmdbPeople people = tmdbApi.getPeople();
    TmdbTV tv = tmdbApi.getTvSeries();
    
    MovieModel movieModel = new MovieModel();
    PeopleModel peopleModel = new PeopleModel();
    TvModel tvModel = new TvModel();
    Search search;

    
    @Before
    public void setup() {
        search = new Search(tmdbApi, movieModel, peopleModel, tvModel);
    }
    
    @Test
    public void stringSearch() {
        search.stringSearch("ryan");
        assertEquals(20, movieModel.getRowCount());
        assertEquals(20, peopleModel.getRowCount());
        assertEquals(20, tvModel.getRowCount());
        
        search.stringSearch("ryan");
        assertEquals(40, movieModel.getRowCount());
        assertEquals(40, peopleModel.getRowCount());
        assertEquals(22, tvModel.getRowCount());
        
    }

    @Test
    public void getMultiImageUrl() {
        MovieDb forbiddenGames = movie.getMovie(5000, "en");
        assertEquals("https://image.tmdb.org/t/p/w185/nhzupVCf3DcqTLdaRSEIaY5KZWc.jpg", search.getMultiImageUrl(forbiddenGames));
        PersonPeople norihiroIsoda = people.getPersonInfo(5000);
        assertEquals("https://image.tmdb.org/t/p/w185", search.getMultiImageUrl(norihiroIsoda));
        TvSeries gameOfThrones = tv.getSeries(1399, "en");
        assertEquals("https://image.tmdb.org/t/p/w185/jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg", search.getMultiImageUrl(gameOfThrones));
        
    }
    
    @Test
    public void getPopularMovies() {
        ArrayList<MovieDb> movieList = search.getPopularMovies();
        assertEquals(20, movieList.size());
    }
    
    @Test
    public void getPersonPeople() {
        PersonPeople norihiroIsoda = people.getPersonInfo(5000);
        assertEquals(norihiroIsoda, search.getPersonPeople((Person) norihiroIsoda));
    }
    
    @Test
    public void getMovieResults() {

        assertTrue(search.getMovieResults(271110).contains("<html>"));
        assertTrue(search.getMovieResults(105290).contains("<html>"));
    }
    
    @Test
    public void getPersonResults() {

        assertTrue(search.getPersonResults(13848).contains("<html>"));
        assertTrue(search.getPersonResults(101616).contains("<html>"));
    }
    
    @Test
    public void getTvResults() {

        assertTrue(search.getTvResults(57243).contains("<html>"));
        assertTrue(search.getTvResults(44285).contains("<html>"));
    }
}
