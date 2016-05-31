package moviepack;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;

public class MovieModelTest {

    MovieModel movieModel;
    MovieDb forbiddenGames;
    MovieDb beforeNightFalls;
    
    @Before
    public void setup() {
        movieModel = new MovieModel();
        TmdbApi tmdbApi = new TmdbApi("811ffa781385ed56e1ec64c193eb93f4");
        TmdbMovies movie = tmdbApi.getMovies();
        movieModel.add(forbiddenGames = movie.getMovie(5000, "en"));
        movieModel.add(beforeNightFalls = movie.getMovie(5001, "en"));
    }
    
    @Test
    public void init() {
        MovieModel movieMod = new MovieModel();
        assertEquals(0, movieMod.getRowCount());
    }


    @Test
    public void getRowCount() {
        assertEquals(2, movieModel.getRowCount());
    }

    @Test
    public void getColumnCount() {
        assertEquals(2, movieModel.getColumnCount());
    }
    
    @Test
    public void getColumnName() {
        assertEquals("Title", movieModel.getColumnName(0));
        assertEquals("Date", movieModel.getColumnName(1));
    }
    
    @Test
    public void getValueAt() {
        assertEquals("Forbidden Games", movieModel.getValueAt(0, 0));
        assertEquals("1952-05-09", movieModel.getValueAt(0, 1));
        assertEquals("Before Night Falls", movieModel.getValueAt(1, 0));
        assertEquals("2000-09-03", movieModel.getValueAt(1, 1));
        assertEquals(0, movieModel.getValueAt(0, 3));
    }


    @Test
    public void get() {
        assertEquals(forbiddenGames, movieModel.get(0));
        assertEquals(beforeNightFalls, movieModel.get(1));
    }

    @Test
    public void add() {
        assertEquals(forbiddenGames, movieModel.get(0));
        movieModel.add(null);
        assertEquals(2, movieModel.getRowCount());
    }

    @Test
    public void clear() {
        movieModel.clear();
        assertEquals(0, movieModel.getRowCount());
        movieModel.clear();
        assertEquals(0, movieModel.getRowCount());
    }

}
