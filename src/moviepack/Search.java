package moviepack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.TmdbPeople.PersonResultsPage;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.TmdbTV;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.Multi;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.people.PersonCrew;
import info.movito.themoviedbapi.model.people.PersonPeople;
import info.movito.themoviedbapi.model.tv.Network;
import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Class that encompasses all search abilities from the TmdB API.
 *
 * @author Nicholas Pahl
 */
public class Search {

    /**
     * Movie model to enter data into the table.
     */
    private MovieModel movieModel;

    /**
     * People model to enter data into the table.
     */
    private PeopleModel peopleModel;

    /**
     * Tv model to enter data into the table.
     */
    private TvModel tvModel;

    /**
     * Allows the looking up of movies.
     */
    private TmdbMovies movies;

    /**
     * Allows the looking up of people.
     */
    private TmdbPeople people;

    /**
     * Allows the looking up of people.
     */
    private TmdbTV tvSeries;

    /**
     * Allows the search functionality .
     */
    private TmdbSearch search;

    /**
     * Previous String searched.
     */
    private String prevStr;

    /**
     * Index of how many searches have happened for the same string.
     */
    private int i = 1;

    /**
     * Initialization of Search that sets the models sets the tmdbapi classes.
     *
     * @param tmdbApi
     *            Api type for tmdbApi classes.
     * @param theMovieModel
     *            The movieModel to be updated.
     * @param thePeopleModel
     *            The peopleModel to be updated.
     * @param theTvModel
     *            The tvModel to be updated.
     */
    public Search(final TmdbApi tmdbApi, final MovieModel theMovieModel,
            final PeopleModel thePeopleModel, final TvModel theTvModel) {
        this.movieModel = theMovieModel;
        this.peopleModel = thePeopleModel;
        this.tvModel = theTvModel;

        people = tmdbApi.getPeople();
        movies = tmdbApi.getMovies();
        tvSeries = tmdbApi.getTvSeries();
        search = tmdbApi.getSearch();
    }

    /**
     * Searches for 20 movies, people, and tv series each from the given string
     * and will add 20 more each time it is called in succession with the same
     * string.
     *
     * @param searchStr
     *            string to be searched.
     */
    public final void stringSearch(final String searchStr) {

        MovieResultsPage moviePage;
        PersonResultsPage personPage;
        TvResultsPage tvPage;

        if (!searchStr.equals(prevStr)) {
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

        while (movieIt.hasNext()) {
            MovieDb movie = movieIt.next();

            movieModel.add(movie);

        }

        while (personIt.hasNext()) {
            Person person = personIt.next();

            peopleModel.add(person);

        }

        while (tvIt.hasNext()) {
            TvSeries tv = tvIt.next();

            tvModel.add(tv);

        }

    }

    /**
     * Returns the url of the image given database object.
     * @param dbObject
     *            the movie, person, or tv show in question.
     * @return the url of the image of the object.
     */
    public final String getMultiImageUrl(final Multi dbObject) {
        String urlString = "https://image.tmdb.org/t/p/w185";
        switch (dbObject.getMediaType()) {
        case MOVIE:
            MovieDb movie = (MovieDb) dbObject;
            urlString += movie.getPosterPath();
            break;
        case PERSON:
            Person person = (Person) dbObject;
            urlString += person.getProfilePath();
            break;
        case TV_SERIES:
            TvSeries tv = (TvSeries) dbObject;
            urlString += tv.getPosterPath();
            break;
        default:
            return "";
        }
        return urlString;
    }
    /**
     * Returns a list of the popular movies.
     * @return a list of popular movies.
     */
    public final ArrayList<MovieDb> getPopularMovies() {

        ArrayList<MovieDb> moviesList = new ArrayList<MovieDb>();

        MovieResultsPage moviesPage = movies.getPopularMovies("en", 1);

        Iterator<MovieDb> moviesIt = moviesPage.iterator();

        while (moviesIt.hasNext()) {
            moviesList.add(moviesIt.next());
        }
        return moviesList;
    }

    /**
     * Turns person into personPeople.
     * @param p Person in question.
     * @return personPeople from person.
     */
    public final PersonPeople getPersonPeople(final Person p) {
        return people.getPersonInfo(p.getId());
    }

    /**
     * Returns a string to be used in a JLabel that contains useful movie
     * information.
     *
     * @param movieId
     *            The movieId to get info of.
     * @return string to be used in a JLabel that contains useful movie
     *         information.
     */
    public final String getMovieResults(final int movieId) {

        MovieDb movie = movies.getMovie(movieId, "en", MovieMethod.credits);

        String resultStr = "<html>";

        // Gets movie Title
        resultStr += "Title: " + movie.getTitle();
        
        // Gets movie date
        if (movie.getReleaseDate() != "") {
            resultStr += "<br>Release Date: " + movie.getReleaseDate();
        }

        // Gets movie director
        List<PersonCrew> crewList = movie.getCrew();

        Iterator<PersonCrew> crewIt = crewList.iterator();

        while (crewIt.hasNext()) {
            PersonCrew person = crewIt.next();

            if (person.getJob().equals("Director")) {

                resultStr += "<br>Director: " + person.getName();
            }
        }

        // Gets Movie genre
        Iterator<Genre> genre = movie.getGenres().iterator();

        if (genre.hasNext()) {
            resultStr += "<br>Genre: ";
        }

        while (genre.hasNext()) {
            resultStr += genre.next().getName() + " ";
        }

        // Gets movie rating
        if (movie.getVoteAverage() != 0) {
            resultStr += "<br>Rating: " + movie.getVoteAverage() + "/10 ("
                    + movie.getVoteCount() + " Votes)";
        }

        resultStr += "</html>";

        return resultStr;
    }

    /**
     * Returns a string to be used in a JLabel that contains useful person
     * information.
     *
     * @param personId
     *            The personId to get info of.
     * @return string to be used in a JLabel that contains useful person
     *         information.
     */
    public final String getPersonResults(final int personId) {

        PersonPeople person = people.getPersonInfo(personId);

        String resultStr = "<html>";

        // get person name
        resultStr += "Name: " + person.getName();

        // get person birthday
        if (person.getBirthday() != "") {
            resultStr += "<br>Birthday: " + person.getBirthday();
        }

        // get person deathday
        if (person.getDeathday() != "") {
            resultStr += "<br>Deathday: " + person.getDeathday();
        }

        // get person birthplace
        if (person.getBirthplace() != "") {
            resultStr += "<br>Birthplace: " + person.getBirthplace();
        }

        resultStr += "</html>";

        return resultStr;
    }

    /**
     * Returns a string to be used in a JLabel that contains useful tv
     * information.
     *
     * @param tvId
     *            The tvId to get info of.
     * @return string to be used in a JLabel that contains useful tv
     *         information.
     */
    public final String getTvResults(final int tvId) {

        TvSeries tv = tvSeries.getSeries(tvId, "en");

        String resultStr = "<html>";

        // get tv name
        resultStr += "Title: " + tv.getOriginalName();

        // get tv first date
        if (tv.getFirstAirDate() != null) {
            resultStr += "<br>First Air Date: " + tv.getFirstAirDate();
        }

        if (tv.getLastAirDate() != null) {
            resultStr += "<br>Last Air Date: " + tv.getLastAirDate();
        }

        // get tv number of seasons
        if (tv.getNumberOfSeasons() != 0) {
            resultStr += "<br>Number of seasons: " + tv.getNumberOfSeasons();
        }

        // get tv network
        Iterator<Network> network = tv.getNetworks().iterator();

        if (network.hasNext()) {
            resultStr += "<br>Network: ";
        }

        while (network.hasNext()) {
            resultStr += network.next().getName() + " ";
        }

        // Gets movie rating
        if (tv.getVoteAverage() != 0) {
            resultStr += "<br>Rating: " + tv.getVoteAverage() + "/10 ("
                    + tv.getVoteCount() + " Votes)";
        }

        resultStr += "</html>";

        return resultStr;
    }
}
