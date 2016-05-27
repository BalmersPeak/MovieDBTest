import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.*;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.keywords.Keyword;
import java.util.Scanner;
import java.util.Set;

 
public class KeywordMatch {

  public static void main(String[] args){
	  	
	  	//Set up System functions
	  	Scanner user_input = new Scanner( System.in );
	  	int i = 0, j =0; 
	  	String str = "";
	  	
	  	//Enter User Key so initialization of data is possible 
		TmdbApi tmdbApi = new TmdbApi("abd5ebb09c79f23b85341363b6b06d54");
		
		//initializes the movie/keyword databases and searches
		TmdbMovies movies = tmdbApi.getMovies();
		TmdbSearch search = tmdbApi.getSearch(); 
		TmdbKeywords TmdbKeyword = tmdbApi.getKeywords();
		
		//Initialize Variables
		List<MovieDb> movielist = null;
		List<MovieDb> movielist2 = null;
		List<MovieDb> movielist3 = null;
		List<MovieDb> movielist4 = null;
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

		//Asks for user input and prints all keywords in system that contain given string
		System.out.println("Enter up to four keywords or \"x\" to complete search: ");
		
		//loops up to four times (4 keywords) unless ended by entering "x" 
		for(i=0; i<4; i++){
			
		str = user_input.next();
		if(str.equals("x")){
			
			System.out.println("END of search... Computing matches..."); 
			break; 
		}

		
		
		info.movito.themoviedbapi.TmdbSearch.KeywordResultsPage keywordresult = search.searchKeyword(str, null);
    
		
		//Gets list of keywords that match user given keyword
		List<Keyword> keywordlist = keywordresult.getResults();
		
		//Grabs first keyword match .. best match
		Keyword mainkeyword = keywordlist.get(0);
		
		//Gets keyword ID and searches for movies that are associated with that keyword ID
		int IDint = mainkeyword.getId(); 
		String IDstring = Integer.toString(IDint);		
		MovieResultsPage results = TmdbKeyword.getKeywordMovies(IDstring, "en", 0); //0 and 1 are both first page 
		
		
		//Gets list of movies that return a match for specified keyword
		//Creates different lists 
		if(i==0){
		 movielist = results.getResults();
		}
		if(i==1){
		 movielist2 = results.getResults();
		}
		if(i==2){
		movielist3 = results.getResults();	
		}
		if(i==3){
		movielist4 = results.getResults();	
		}
		
		//Determines the number of pages needed 
		
		int numofresults = results.getTotalResults(); 
		int numofpages = (numofresults / 20);
		if (numofresults % 20 >= 1){
			++numofpages;
		}
		
		
		//Prints first results
		//	System.out.println(mainkeyword.getName() + ": " + results.getResults());
		

		//creates a new, complete list, one for each keyword
		//ONE LIST INCLUDING ALL RESULTS so can be searched within each list
		
		if(numofpages >= 2){
			for(int k=2; k<=numofpages; k++){
				results = TmdbKeyword.getKeywordMovies(IDstring, "en", k);
			//	System.out.println("Next:" +results.getResults()); //should be 7 pages for summer
				
				if(i==0){
				    movielist.addAll(results.getResults());  
				}
				if(i==1){
					movielist2.addAll(results.getResults());  
	
				}
				if(i==2){
					movielist3.addAll(results.getResults());  
				}
				
				if(i==3){
					movielist4.addAll(results.getResults());  

				}			
				
			}
 		}
		
		
  }
		//Runs if the user exits the search by entering four search keywords instead of entering "x"
		int compare = str.compareTo("x");
		if(compare != 0)
		{
			System.out.println("Max entered. END of search... Computing matches...");
		}
  
		//Matching Algorithm

		//For One keyword search 
		if(i==1){
			System.out.println("Matches are: " + movielist);
		}
		
		
		//////////////////////////////////////////////////////////////
		//Matching Algorithm
		//////////////////////////////////////////////////////////////
		
		//For two keywords
		if(i==2){
	
			
		    //keeps all movies in list one that are also in list two
			movielist.retainAll(movielist2); 		
			System.out.println("Matches are:" + movielist);
			 
		}
		
		
		//For Three keywords
		if(i==3){
			
			//Creates a copy of list one so it can be checked twice 
			templist = new ArrayList<MovieDb>(movielist);
			 
			//Finds "Second best" matches in First list
			movielist.retainAll(movielist2);
			secondbest = new ArrayList<MovieDb>(movielist);
			
			templist.retainAll(movielist3);
			secondbest2 = new ArrayList<MovieDb>(templist);
			
			
			//Finds "Second best" matches in Second list
			movielist2.retainAll(movielist3);
			secondbest3 = new ArrayList<MovieDb>(movielist2);
		
			//Finds "Best match"
			bestmatch = new ArrayList<MovieDb>(secondbest);
			bestmatch.retainAll(movielist3);
			
			//Gets rid of repeats 
			secondbestall = new ArrayList<MovieDb>(secondbest);
			secondbestall.addAll(secondbest2);
			secondbestall.addAll(secondbest3);
			
			Set<MovieDb> hs = new HashSet<>();
			hs.addAll(secondbestall);
			secondbestall.clear();
			secondbestall.addAll(hs);
			
			
			//Prints out matches
			System.out.println("Perfect matches are: " + bestmatch);
			System.out.println("Second best matches are: " + secondbestall);
			
			
		}
		
		//For Four keywords
		if(i==4){
			
			templist = new ArrayList<MovieDb>(movielist);
			templist2 = new ArrayList<MovieDb>(movielist);
			
			l2templist = new ArrayList<MovieDb>(movielist2);
			
			//Finds 3rd Best Matches from List 1
			movielist.retainAll(movielist2);
			thirdbest = new ArrayList<MovieDb>(movielist);
			l1thirdbest = new ArrayList<MovieDb>(movielist);
			
			templist.retainAll(movielist3);
			thirdbest2 = new ArrayList<MovieDb>(templist);
			
			templist2.retainAll(movielist4);
			thirdbest3 = new ArrayList<MovieDb>(templist2);
			
			//Finds 3rd Best Matches from List 2 
			movielist2.retainAll(movielist3);
			thirdbest4 = new ArrayList<MovieDb>(movielist2);
			
			l2templist.retainAll(movielist4);
			thirdbest5 = new ArrayList<MovieDb>(l2templist);
			
			//Finds 3rd Best from List 3
			l3templist = new ArrayList<MovieDb>(movielist3);
			movielist3.retainAll(movielist4);
			thirdbest6 = new ArrayList<MovieDb>(movielist3);
			
			//Finds 2nd Best from List 1
			secondbest = new ArrayList<MovieDb>(thirdbest);
			secondbest.retainAll(l3templist);
			 
			secondbest2 = new ArrayList<MovieDb>(l1thirdbest);
			secondbest2.retainAll(movielist4);
			
			secondbest3 = new ArrayList<MovieDb>(thirdbest2);
			secondbest3.retainAll(movielist4);
			
			
			//Finds 2nd Best from List 2 
			secondbest4 = new ArrayList<MovieDb>(thirdbest4);
			secondbest4.retainAll(movielist4);
			
			secondbest5 = new ArrayList<MovieDb>(thirdbest5);
			secondbest5.retainAll(l3templist);
			
			//Finds Best Match
			bestmatch = new ArrayList<MovieDb>(secondbest);
			bestmatch.retainAll(movielist4);
			
			//Gets rid of repeats 
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
			
			//Prints out matches
			System.out.println("Perfect matches are: " + bestmatch);
			System.out.println("Second best matches are: " + secondbestall);
			System.out.println("Third best matches are: " + thirdbestall);
			
		}
		
}}