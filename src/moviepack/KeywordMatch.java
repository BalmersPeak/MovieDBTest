package moviepack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbKeywords;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.TmdbSearch.KeywordResultsPage;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.keywords.Keyword;

/**
 * Keyword Match and GUI results. This is the algorithm for the keyword matching
 * Displays best matches for up to four keywords
 *
 * @author Janelle Zuccaro
 */

public class KeywordMatch {

    /**
     * Access to the search function of the API.
     *
     */
    private TmdbSearch search;

    /**
     * Access to the keyword list and relationships of API.
     *
     */
    private TmdbKeywords tmdbKeyword;

    /**
     * Creates a display model for the GUI.
     *
     */
    private MovieModel keywordModel;
    
    /**
     * Access to movie information. 
     */
    private TmdbMovies movies;

    /**
     * keeps track of suggested keywords
     */
   public  List<String> keyString = new ArrayList<String>();
    /**
     * The maximum number of keywords.
     */
    private static final int MAX_KEYWORDS = 4;

    /**
     * Number of movies per page.
     */
    private static final int MOVIES_PER_PAGE = 20;
    
   private List<Keyword> currentKeyList = new ArrayList<Keyword>();
   private List<Keyword> suggestedKeywords = new ArrayList<Keyword>();
   private Keyword mainkeyword = null;

    /**
     * Initializes keywords from database.
     *
     * @param tmdbApi
     *            Grants access to API information
     * @param theKeywordModel
     *            Creates a model for keywords in GUI.
     */
    public KeywordMatch(final TmdbApi tmdbApi,
            final MovieModel theKeywordModel) {

        search = tmdbApi.getSearch();
        tmdbKeyword = tmdbApi.getKeywords();
        movies = tmdbApi.getMovies();

        this.keywordModel = theKeywordModel;

    }

    /**
     * Searches movies that contain the given keyword.
     *
     * @param keywords
     *            User entry of searched keyword.
     */
    public final void searchKeyword(final String keywords) {

        int i = 0;
        String str = "";

        keywordModel.clear();

        // Initialize Variables
        List<MovieDb> movielist = null;
        List<MovieDb> movielist2 = null;
        List<MovieDb> movielist3 = null;
        List<MovieDb> movielist4 = null;

        List<String> keywordList = Arrays.asList(keywords.split(","));

        // loops up to four times (4 keywords) unless ended by entering "x"
        for (i = 0; i < keywordList.size() && i < MAX_KEYWORDS; i++) {

            str = keywordList.get(i);

            KeywordResultsPage keywordresult = search.searchKeyword(str, null);

            // Gets list of keywords that match user given keyword
            List<Keyword> keywordlist = keywordresult.getResults();

            MovieResultsPage results = new MovieResultsPage();
            int j = 0;
            String idString = "";
            boolean badKeyword = false;

            while (true) {
                try {
                    // Grabs first keyword match .. best match
                    mainkeyword = keywordlist.get(j);

                    // Gets keyword ID and searches for movies that are
                    // associated with
                    // that keyword ID
                    int idInt = mainkeyword.getId();

                    idString = Integer.toString(idInt);

                    results = tmdbKeyword.getKeywordMovies(idString, "en", 0);
                    // 0 and 1 are both first page
                } catch (IndexOutOfBoundsException e) {
                    badKeyword = true;
                    break;
                } catch (Exception e) {
                    j++;
                    continue;
                }
                break;
            }

            if (badKeyword) {
                break;
            }

            // Gets list of movies that return a match for specified keyword
            // Creates different lists for each keyword
            if (i == 0) {
                movielist = results.getResults();
            }
            if (i == 1) {
                movielist2 = results.getResults();
            }
            if (i == 2) {
                movielist3 = results.getResults();
            }
            if (i == MAX_KEYWORDS - 1) {
                movielist4 = results.getResults();
            }

            // Determines the number of pages needed

            int numofresults = results.getTotalResults();
            int numofpages = (numofresults / MOVIES_PER_PAGE);
            if (numofresults % MOVIES_PER_PAGE >= 1) {
                ++numofpages;
            }

            // creates a new, complete list, one for each keyword
            // ONE LIST INCLUDING ALL RESULTS so can be searched within each
            // list

            if (numofpages >= 2) {
                for (int k = 2; k <= numofpages; k++) {
                    results = tmdbKeyword.getKeywordMovies(idString, "en", k);

                    if (i == 0) {
                        movielist.addAll(results.getResults());
                    }
                    if (i == 1) {
                        movielist2.addAll(results.getResults());

                    }
                    if (i == 2) {
                        movielist3.addAll(results.getResults());
                    }

                    if (i == MAX_KEYWORDS - 1) {
                        movielist4.addAll(results.getResults());

                    }

                }
            }
        }
        matchingAlgorihm(i, movielist, movielist2, movielist3, movielist4);
    }

    /**
     * Puts the movie results into the movie model.
     * @param i number of keywords.
     * @param movielist
     *            list from first keyword.
     * @param movielist2
     *            list from second keyword.
     * @param movielist3
     *            list from third keyword.
     * @param movielist4
     *            list from fourth keyword.
     */
    private void matchingAlgorihm(final int i, final List<MovieDb> movielist,
            final List<MovieDb> movielist2, final List<MovieDb> movielist3,
            final List<MovieDb> movielist4) {

        // Runs if the user exits the search by entering four search keywords
        // instead of entering "x"


        // Matching Algorithm

        // For One keyword search
        if (i == 1) {
        	keyString.clear();
            for (MovieDb movie : movielist) {
                keywordModel.add(movie);
            }
        }

        //////////////////////////////////////////////////////////////
        // Matching Algorithm
        //////////////////////////////////////////////////////////////
       
        
		
        // For two keywords
        if (i == 2) {

            // keeps all movies in list one that are also in list two
            movielist.retainAll(movielist2);

            int size = movielist.size();
            int count;

			for(count=0; count<size; count++){

				MovieDb currentmovie = movielist.get(count); /// this works
				int movieId = currentmovie.getId();
				currentKeyList = movies.getKeywords(movieId);
				suggestedKeywords.addAll(currentKeyList);
			}
			
				//gets rid of repeats
				Set<Keyword> hs = new HashSet<>();
				hs.addAll(suggestedKeywords);
				suggestedKeywords.clear();
				suggestedKeywords.addAll(hs);
				suggestedKeywords.remove(mainkeyword);

			keyString.clear();
		
			for(Keyword keyword : suggestedKeywords){
			
				keyString.add(keyword.getName()+"\n");
				
				
			}

            for (MovieDb movie : movielist) {
                keywordModel.add(movie);
            }
        }


        // For Three keywords
        if (i == MAX_KEYWORDS - 1) {
            threeKeywordMatch(movielist, movielist2, movielist3);
        }

        // For Four keywords
        if (i == MAX_KEYWORDS) {
            fourKeywordMatch(movielist, movielist2, movielist3, movielist4);
        }
    }

    /**
     * Matches movies for three keywords.
     * @param movielist
     *            list from first keyword.
     * @param movielist2
     *            list from second keyword.
     * @param movielist3
     *            list from third keyword.
     */
    private void threeKeywordMatch(final List<MovieDb> movielist,
            final List<MovieDb> movielist2, final List<MovieDb> movielist3) {

        List<MovieDb> templist = null;
        List<MovieDb> bestmatch = null;
        List<MovieDb> secondbest = null;
        List<MovieDb> secondbest2 = null;
        List<MovieDb> secondbest3 = null;
        List<MovieDb> secondbestall = null;

        // Creates a copy of list one so it can be checked twice
        templist = new ArrayList<MovieDb>(movielist);

        // Finds "Second best" matches in First list
        movielist.retainAll(movielist2);
        secondbest = new ArrayList<MovieDb>(movielist);

        templist.retainAll(movielist3);
        secondbest2 = new ArrayList<MovieDb>(templist);

        // Finds "Second best" matches in Second list
        movielist2.retainAll(movielist3);
        secondbest3 = new ArrayList<MovieDb>(movielist2);

        // Finds "Best match"
        bestmatch = new ArrayList<MovieDb>(secondbest);
        bestmatch.retainAll(movielist3);

        // Gets rid of repeats
        secondbestall = new ArrayList<MovieDb>(secondbest);
        secondbestall.addAll(secondbest2);
        secondbestall.addAll(secondbest3);

        Set<MovieDb> hs = new HashSet<>();
        hs.addAll(secondbestall);
        secondbestall.clear();
        secondbestall.addAll(hs);

        
        int size = bestmatch.size();
        int count;

		for(count=0; count<size; count++){

			MovieDb currentmovie = movielist.get(count); /// this works
			int movieId = currentmovie.getId();
			currentKeyList = movies.getKeywords(movieId);
			suggestedKeywords.addAll(currentKeyList);
		}
		
			//gets rid of repeats
			Set<Keyword> hs3 = new HashSet<>();
			hs3.addAll(suggestedKeywords);
			suggestedKeywords.clear();
			suggestedKeywords.addAll(hs3);
			suggestedKeywords.remove(mainkeyword);
			
			 
		keyString.clear();

		for(Keyword keyword : suggestedKeywords){
		
			keyString.add(keyword.getName()+"\n");
			
			
		}
        // Prints out matches

        for (MovieDb movie : bestmatch) {
            keywordModel.add(movie);
        }

        for (MovieDb movie : secondbestall) {
            keywordModel.add(movie);
        }
    }

    /**
     * Matches movies for four keywords.
     * @param movielist
     *            list from first keyword.
     * @param movielist2
     *            list from second keyword.
     * @param movielist3
     *            list from third keyword.
     * @param movielist4
     *            list from fourth keyword.
     */
    private void fourKeywordMatch(final List<MovieDb> movielist,
            final List<MovieDb> movielist2, final List<MovieDb> movielist3,
            final List<MovieDb> movielist4) {

        List<MovieDb> templist = null;
        List<MovieDb> templist2 = null;
        List<MovieDb> l2templist = null;
        List<MovieDb> l3templist = null;
        List<MovieDb> bestmatch = null;
        List<MovieDb> secondbest = null;
        List<MovieDb> secondbest2 = null;
        List<MovieDb> secondbest3 = null;
        List<MovieDb> secondbest4 = null;
        List<MovieDb> secondbest5 = null;
        List<MovieDb> secondbestall = null;
        List<MovieDb> thirdbest = null;
        List<MovieDb> l1thirdbest = null;
        List<MovieDb> thirdbest2 = null;
        List<MovieDb> thirdbest3 = null;
        List<MovieDb> thirdbest4 = null;
        List<MovieDb> thirdbest5 = null;
        List<MovieDb> thirdbest6 = null;
        List<MovieDb> thirdbestall = null;

        templist = new ArrayList<MovieDb>(movielist);
        templist2 = new ArrayList<MovieDb>(movielist);

        l2templist = new ArrayList<MovieDb>(movielist2);

        // Finds 3rd Best Matches from List 1
        movielist.retainAll(movielist2);
        thirdbest = new ArrayList<MovieDb>(movielist);
        l1thirdbest = new ArrayList<MovieDb>(movielist);

        templist.retainAll(movielist3);
        thirdbest2 = new ArrayList<MovieDb>(templist);

        templist2.retainAll(movielist4);
        thirdbest3 = new ArrayList<MovieDb>(templist2);

        // Finds 3rd Best Matches from List 2
        movielist2.retainAll(movielist3);
        thirdbest4 = new ArrayList<MovieDb>(movielist2);

        l2templist.retainAll(movielist4);
        thirdbest5 = new ArrayList<MovieDb>(l2templist);

        // Finds 3rd Best from List 3
        l3templist = new ArrayList<MovieDb>(movielist3);
        movielist3.retainAll(movielist4);
        thirdbest6 = new ArrayList<MovieDb>(movielist3);

        // Finds 2nd Best from List 1
        secondbest = new ArrayList<MovieDb>(thirdbest);
        secondbest.retainAll(l3templist);

        secondbest2 = new ArrayList<MovieDb>(l1thirdbest);
        secondbest2.retainAll(movielist4);

        secondbest3 = new ArrayList<MovieDb>(thirdbest2);
        secondbest3.retainAll(movielist4);

        // Finds 2nd Best from List 2
        secondbest4 = new ArrayList<MovieDb>(thirdbest4);
        secondbest4.retainAll(movielist4);

        secondbest5 = new ArrayList<MovieDb>(thirdbest5);
        secondbest5.retainAll(l3templist);

        // Finds Best Match
        bestmatch = new ArrayList<MovieDb>(secondbest);
        bestmatch.retainAll(movielist4);

        // Gets rid of repeats
        secondbestall = new ArrayList<MovieDb>(secondbest);
        secondbestall.addAll(secondbest2);
        secondbestall.addAll(secondbest3);
        secondbestall.addAll(secondbest4);
        secondbestall.addAll(secondbest5);

        thirdbestall = new ArrayList<MovieDb>(thirdbest);
        thirdbestall.addAll(thirdbest2);
        thirdbestall.addAll(thirdbest3);
        thirdbestall.addAll(thirdbest4);
        thirdbestall.addAll(thirdbest5);
        thirdbestall.addAll(thirdbest6);

        Set<MovieDb> hs = new HashSet<>();
        hs.addAll(secondbestall);
        secondbestall.clear();
        secondbestall.addAll(hs);

        Set<MovieDb> hs2 = new HashSet<>();
        hs2.addAll(thirdbestall);
        thirdbestall.clear();
        thirdbestall.addAll(hs2);

        // Prints out matches
        keyString.clear();
        
        for (MovieDb movie : bestmatch) {
            keywordModel.add(movie);
        }

        for (MovieDb movie : secondbestall) {
            keywordModel.add(movie);
        }

        for (MovieDb movie : thirdbestall) {
            keywordModel.add(movie);
        }
    }
    public List<String> getSuggested(){
    	return keyString;
    }
}
