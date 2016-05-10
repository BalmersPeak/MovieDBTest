import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.*;

public class Test {
	public static void main(String[] args){
		TmdbApi tmdbApi = new TmdbApi("811ffa781385ed56e1ec64c193eb93f4");
		
		TmdbMovies movies = tmdbApi.getMovies();
		
		MovieDb movie = movies.getMovie(5000, "en");
		System.out.println(movie.getTitle());
		System.out.println(movie.getReleaseDate());
		System.out.println(movie.getRuntime());
	}
}
